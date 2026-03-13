package com.example.backend.dto;

public class Forecast {

    private DailyForecast daily;

    public DailyForecast getDaily() {
        return daily;
    }

    public void setDaily(DailyForecast daily) {
        this.daily = daily;
    }
}