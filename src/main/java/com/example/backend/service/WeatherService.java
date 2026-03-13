package com.example.backend.service;

import com.example.backend.dto.AirQualityResponse;
import com.example.backend.dto.AirQualityResultDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherService {

    public String city;

    public Double lat;
    public Double lon;

    public String API_URL =
            "https://api.waqi.info/feed/geo:3.1390;101.6869/?token=93281fbcab41116ecfea291c83c8ec8b70a03a1b";

    public AirQualityResultDto getWeather() {
        RestTemplate restTemplate = new RestTemplate();

        AirQualityResponse response =
                restTemplate.getForObject(API_URL, AirQualityResponse.class);

        if (response == null || response.getData() == null) {
            throw new RuntimeException("Failed to get air quality data from API.");
        }

        AirQualityResultDto result = new AirQualityResultDto();

        result.setAqi(response.getData().getAqi());
        result.setDominantPollutant(response.getData().getDominentpol());

        if (response.getData().getCity() != null) {
            result.setCityName(response.getData().getCity().getName());

            List<Double> geo = response.getData().getCity().getGeo();
            if (geo != null && geo.size() >= 2) {
                result.setLatitude(geo.get(0));
                result.setLongitude(geo.get(1));
            }
        }

        if (response.getData().getIaqi() != null) {
            if (response.getData().getIaqi().getT() != null) {
                result.setTemperature(response.getData().getIaqi().getT().getV());
            }
            if (response.getData().getIaqi().getH() != null) {
                result.setHumidity(response.getData().getIaqi().getH().getV());
            }
            if (response.getData().getIaqi().getP() != null) {
                result.setPressure(response.getData().getIaqi().getP().getV());
            }
            if (response.getData().getIaqi().getW() != null) {
                result.setWindSpeed(response.getData().getIaqi().getW().getV());
            }
            if (response.getData().getIaqi().getWg() != null) {
                result.setWindGust(response.getData().getIaqi().getWg().getV());
            }
        }

        if (response.getData().getForecast() != null &&
                response.getData().getForecast().getDaily() != null) {

            result.setPm25Forecast(
                    response.getData().getForecast().getDaily().getPm25()
            );
        }

        if (response.getData().getTime() != null) {
            result.setRecordTime(response.getData().getTime().getIso());
        }

        result.setRecommendation(generateRecommendation(result.getAqi()));

        return result;
    }

    private String generateRecommendation(Integer aqi) {
        if (aqi == null) {
            return "No recommendation available.";
        }

        if (aqi <= 50) {
            return "Air quality is good. Outdoor activities are recommended.";
        } else if (aqi <= 100) {
            return "Air quality is moderate. Sensitive groups should be cautious.";
        } else if (aqi <= 150) {
            return "Air quality is unhealthy for sensitive groups. Reduce outdoor activities.";
        } else {
            return "Air quality is unhealthy. Avoid prolonged outdoor activities.";
        }
    }

    public AirQualityResultDto getByCity(String city) {
        RestTemplate restTemplate = new RestTemplate();

        String url="https://api.waqi.info/feed/"+city+"/?token=93281fbcab41116ecfea291c83c8ec8b70a03a1b";

        AirQualityResponse response =
                restTemplate.getForObject(url, AirQualityResponse.class);

        if (response == null || response.getData() == null) {
            throw new RuntimeException("Failed to get air quality data from API.");
        }

        AirQualityResultDto result = new AirQualityResultDto();

        result.setAqi(response.getData().getAqi());
        result.setDominantPollutant(response.getData().getDominentpol());

        if (response.getData().getCity() != null) {
            result.setCityName(response.getData().getCity().getName());

            List<Double> geo = response.getData().getCity().getGeo();
            if (geo != null && geo.size() >= 2) {
                result.setLatitude(geo.get(0));
                result.setLongitude(geo.get(1));
            }
        }

        if (response.getData().getIaqi() != null) {
            if (response.getData().getIaqi().getT() != null) {
                result.setTemperature(response.getData().getIaqi().getT().getV());
            }
            if (response.getData().getIaqi().getH() != null) {
                result.setHumidity(response.getData().getIaqi().getH().getV());
            }
            if (response.getData().getIaqi().getP() != null) {
                result.setPressure(response.getData().getIaqi().getP().getV());
            }
            if (response.getData().getIaqi().getW() != null) {
                result.setWindSpeed(response.getData().getIaqi().getW().getV());
            }
            if (response.getData().getIaqi().getWg() != null) {
                result.setWindGust(response.getData().getIaqi().getWg().getV());
            }
        }

        if (response.getData().getForecast() != null &&
                response.getData().getForecast().getDaily() != null) {

            result.setPm25Forecast(
                    response.getData().getForecast().getDaily().getPm25()
            );
        }

        if (response.getData().getTime() != null) {
            result.setRecordTime(response.getData().getTime().getIso());
        }

        result.setRecommendation(generateRecommendation(result.getAqi()));

        return result;
    }

    public AirQualityResultDto getByGeo(Double lat, Double lon) {
        RestTemplate restTemplate = new RestTemplate();

        String Latitude = lat.toString();
        String Longitude = lon.toString();

        String url = "https://api.waqi.info/feed/geo:" + Latitude + ";" + Longitude + "/?token=93281fbcab41116ecfea291c83c8ec8b70a03a1b";


        AirQualityResponse response =
                restTemplate.getForObject(url, AirQualityResponse.class);

        if (response == null || response.getData() == null) {
            throw new RuntimeException("Failed to get air quality data from API.");
        }

        AirQualityResultDto result = new AirQualityResultDto();

        result.setAqi(response.getData().getAqi());
        result.setDominantPollutant(response.getData().getDominentpol());

        if (response.getData().getCity() != null) {
            result.setCityName(response.getData().getCity().getName());

            List<Double> geo = response.getData().getCity().getGeo();
            if (geo != null && geo.size() >= 2) {
                result.setLatitude(geo.get(0));
                result.setLongitude(geo.get(1));
            }
        }

        if (response.getData().getIaqi() != null) {
            if (response.getData().getIaqi().getT() != null) {
                result.setTemperature(response.getData().getIaqi().getT().getV());
            }
            if (response.getData().getIaqi().getH() != null) {
                result.setHumidity(response.getData().getIaqi().getH().getV());
            }
            if (response.getData().getIaqi().getP() != null) {
                result.setPressure(response.getData().getIaqi().getP().getV());
            }
            if (response.getData().getIaqi().getW() != null) {
                result.setWindSpeed(response.getData().getIaqi().getW().getV());
            }
            if (response.getData().getIaqi().getWg() != null) {
                result.setWindGust(response.getData().getIaqi().getWg().getV());
            }
        }

        if (response.getData().getForecast() != null &&
                response.getData().getForecast().getDaily() != null) {

            result.setPm25Forecast(
                    response.getData().getForecast().getDaily().getPm25()
            );
        }

        if (response.getData().getTime() != null) {
            result.setRecordTime(response.getData().getTime().getIso());
        }

        result.setRecommendation(generateRecommendation(result.getAqi()));

        return result;
    }
}