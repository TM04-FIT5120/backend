package com.example.backend.dto;

import java.util.List;

public class City {

    private List<Double> geo;
    private String name;

    public List<Double> getGeo() {
        return geo;
    }

    public void setGeo(List<Double> geo) {
        this.geo = geo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}