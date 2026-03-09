package com.example.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String API_URL =
            "https://api.waqi.info/feed/here/?token=93281fbcab41116ecfea291c83c8ec8b70a03a1b";

    public String getWeather() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(API_URL, String.class);
    }
}