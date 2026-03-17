package com.example.backend.dto;

import java.util.List;

public class CityAqiForecastDto {

    private String cityName;
    private List<MonthlyAqiPredictionDto> predictions;

    public CityAqiForecastDto() {
    }

    public CityAqiForecastDto(String cityName, List<MonthlyAqiPredictionDto> predictions) {
        this.cityName = cityName;
        this.predictions = predictions;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<MonthlyAqiPredictionDto> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<MonthlyAqiPredictionDto> predictions) {
        this.predictions = predictions;
    }
}

