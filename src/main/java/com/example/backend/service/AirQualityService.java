package com.example.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AirQualityService {

    @Value("${waqi.token}")
    private String token;

    private final String API_URL = "http://api.waqi.info/feed/geo:22.54;114.06/?token="+token;

    public String getAirQuality() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL, String.class);
        return response;
    }
}