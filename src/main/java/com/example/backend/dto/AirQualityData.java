package com.example.backend.dto;

public class AirQualityData {

    private Integer aqi;
    private City city;
    private String dominentpol;
    private Iaqi iaqi;
    private TimeInfo time;

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getDominentpol() {
        return dominentpol;
    }

    public void setDominentpol(String dominentpol) {
        this.dominentpol = dominentpol;
    }

    public Iaqi getIaqi() {
        return iaqi;
    }

    public void setIaqi(Iaqi iaqi) {
        this.iaqi = iaqi;
    }

    public TimeInfo getTime() {
        return time;
    }

    public void setTime(TimeInfo time) {
        this.time = time;
    }
}