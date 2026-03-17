package com.example.backend.dto;

public class MonthlyAqiPredictionDto {

    private String month; // e.g. "2026-04"
    private Double predictedAqi;

    public MonthlyAqiPredictionDto() {
    }

    public MonthlyAqiPredictionDto(String month, Double predictedAqi) {
        this.month = month;
        this.predictedAqi = predictedAqi;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getPredictedAqi() {
        return predictedAqi;
    }

    public void setPredictedAqi(Double predictedAqi) {
        this.predictedAqi = predictedAqi;
    }
}

