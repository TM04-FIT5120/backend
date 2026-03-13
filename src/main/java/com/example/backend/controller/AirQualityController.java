package com.example.backend.controller;

import com.example.backend.dto.AirQualityResultDto;
import com.example.backend.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/airQuality")
public class AirQualityController {

    private final WeatherService weatherService;

    public AirQualityController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/airQualityDefaut")
    public AirQualityResultDto getAirQuality() {
        return weatherService.getWeather();
    }

    @GetMapping("/airQualityByCityOrLocation")
    public AirQualityResultDto getAirQuality(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Double Latitude,
            @RequestParam(required = false) Double Longitude) {

        if (city != null) {
            return weatherService.getByCity(city);
        }

        if (Latitude != null && Longitude != null) {
            return weatherService.getByGeo(Latitude, Longitude);
        }

        throw new RuntimeException("City or coordinates must be provided");
    }


}