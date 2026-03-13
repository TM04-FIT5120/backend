package com.example.backend.dto;

public class AirQualityResponse {

    private String status;
    private AirQualityData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AirQualityData getData() {
        return data;
    }

    public void setData(AirQualityData data) {
        this.data = data;
    }
}