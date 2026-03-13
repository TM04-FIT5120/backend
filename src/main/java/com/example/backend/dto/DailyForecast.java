package com.example.backend.dto;

import java.util.List;

public class DailyForecast {

    private List<ForecastItem> pm25;

    public List<ForecastItem> getPm25() {
        return pm25;
    }

    public void setPm25(List<ForecastItem> pm25) {
        this.pm25 = pm25;
    }
}