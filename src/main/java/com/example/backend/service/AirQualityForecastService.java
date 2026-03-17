package com.example.backend.service;

import com.example.backend.dto.CityAqiForecastDto;
import com.example.backend.dto.MonthlyAqiPredictionDto;
import com.example.backend.entity.AirQualityEntity;
import com.example.backend.repository.AirQualityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AirQualityForecastService {

    private final AirQualityRepository airQualityRepository;

    public AirQualityForecastService(AirQualityRepository airQualityRepository) {
        this.airQualityRepository = airQualityRepository;
    }

    public List<CityAqiForecastDto> predictNext12MonthsForAllCities() {
        // Use all available history in the table instead of cutting strictly
        // to the last 24 months. This ensures we return predictions even if
        // the dataset is slightly shorter than 2 years for some cities.
        List<AirQualityEntity> history = airQualityRepository.findAll();

        if (history.isEmpty()) {
            return List.of();
        }

        // group by city, then by YearMonth, compute monthly average AQI
        Map<String, Map<YearMonth, Double>> cityMonthlyAvg = history.stream()
                .filter(e -> e.getCityName() != null && e.getAqi() != null)
                .collect(Collectors.groupingBy(
                        AirQualityEntity::getCityName,
                        Collectors.groupingBy(
                                e -> YearMonth.from(e.getRecordTime()),
                                Collectors.collectingAndThen(
                                        Collectors.summarizingDouble(e -> e.getAqi().doubleValue()),
                                        DoubleSummaryStatistics::getAverage
                                )
                        )
                ));

        List<CityAqiForecastDto> result = new ArrayList<>();

        for (Map.Entry<String, Map<YearMonth, Double>> cityEntry : cityMonthlyAvg.entrySet()) {
            String cityName = cityEntry.getKey();
            Map<YearMonth, Double> monthly = cityEntry.getValue();

            if (monthly.isEmpty()) {
                continue;
            }

            // sort historical months
            List<Map.Entry<YearMonth, Double>> sorted = monthly.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toList());

            int n = sorted.size();

            // If we only have 1-2 months of data, fall back to a simple
            // constant model using the average AQI, so that we still return
            // useful predictions instead of nothing.
            double a;
            double b;
            if (n == 1) {
                a = sorted.get(0).getValue();
                b = 0.0;
            } else {
                // simple linear regression y = a + b * t  (t = 0..n-1)
                double sumT = 0.0;
                double sumY = 0.0;
                double sumT2 = 0.0;
                double sumTY = 0.0;

                for (int i = 0; i < n; i++) {
                    double t = i;
                    double y = sorted.get(i).getValue();
                    sumT += t;
                    sumY += y;
                    sumT2 += t * t;
                    sumTY += t * y;
                }

                double denominator = n * sumT2 - sumT * sumT;
                if (denominator == 0.0) {
                    // fall back to a flat average model
                    a = sumY / n;
                    b = 0.0;
                } else {
                    b = (n * sumTY - sumT * sumY) / denominator;
                    a = (sumY - b * sumT) / n;
                }
            }

            // predict next 12 months starting from next calendar month
            // relative to "now" (current year/month)
            // This keeps the forecast up-to-date to the user's current date.
            YearMonth startMonth = YearMonth.from(LocalDate.now()).plusMonths(1);
            List<MonthlyAqiPredictionDto> predictions = new ArrayList<>();

            for (int i = 0; i < 12; i++) {
                int tFuture = n + i;
                double predicted = a + b * tFuture;
                if (predicted < 0) {
                    predicted = 0;
                }
                YearMonth ym = startMonth.plusMonths(i);
                String monthStr = ym.toString(); // e.g. "2026-04"
                predictions.add(new MonthlyAqiPredictionDto(monthStr, predicted));
            }

            // keep predictions for cities that have at least one non-null prediction
            if (!predictions.isEmpty()) {
                // ensure insertion order is predictable by sorting by YearMonth string
                List<MonthlyAqiPredictionDto> ordered = predictions.stream()
                        .sorted(Comparator.comparing(MonthlyAqiPredictionDto::getMonth))
                        .collect(Collectors.toCollection(ArrayList::new));

                result.add(new CityAqiForecastDto(cityName, ordered));
            }
        }

        // Sort result by city name for stable output
        result.sort(Comparator.comparing(CityAqiForecastDto::getCityName));

        return result;
    }
}

