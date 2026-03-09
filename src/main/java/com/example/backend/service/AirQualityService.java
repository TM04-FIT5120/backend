package com.example.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AirQualityService {

    private final String API_URL = "http://api.waqi.info/feed/geo:22.54;114.06/?token=9923aa8cfcfc539c9d07b91ea";

    public String getAirQuality() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL, String.class);
        return response;
    }
}