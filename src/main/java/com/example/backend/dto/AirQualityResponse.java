package com.example.backend.dto;

public class AirQualityResponse {
    private String status;
    private AirQualityData data;
    private Debug debug;

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

    public Debug getDebug() {
        return debug;
    }

    public void setDebug(Debug debug) {
        this.debug = debug;
    }
}