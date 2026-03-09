package com.example.backend.controller;

import com.example.backend.service.AirQualityService;
import com.example.backend.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirQualityController {

    private final AirQualityService airQualityService;

    private final WeatherService weatherService;

    public AirQualityController(AirQualityService airQualityService, WeatherService weatherService) {
        this.airQualityService = airQualityService;
        this.weatherService = weatherService;
    }

    @GetMapping("/air")
    public String getAirData() {
        return airQualityService.getAirQuality();
    }

    @GetMapping("/weather")
    public String getweatherData() {
        return weatherService.getWeather();
    }
}