package com.example.backend.dto;

/**
 * Individual Air Quality Indicators
 * 空气质量和环境指标
 */
public class Iaqi {

    /** Air Quality Index 空气质量指数 */
    private ValueWrapper aqi;

    /** Dew Point 露点温度 */
    private ValueWrapper dew;

    /** Humidity 湿度 (%) */
    private ValueWrapper h;

    /** Pressure 气压 (hPa) */
    private ValueWrapper p;

    /** Temperature 温度 (°C) */
    private ValueWrapper t;

    /** Wind Speed 风速 (m/s) */
    private ValueWrapper w;

    /** Wind Gust 阵风速度 (m/s) */
    private ValueWrapper wg;

    public ValueWrapper getAqi() {
        return aqi;
    }

    public void setAqi(ValueWrapper aqi) {
        this.aqi = aqi;
    }

    public ValueWrapper getDew() {
        return dew;
    }

    public void setDew(ValueWrapper dew) {
        this.dew = dew;
    }

    public ValueWrapper getH() {
        return h;
    }

    public void setH(ValueWrapper h) {
        this.h = h;
    }

    public ValueWrapper getP() {
        return p;
    }

    public void setP(ValueWrapper p) {
        this.p = p;
    }

    public ValueWrapper getT() {
        return t;
    }

    public void setT(ValueWrapper t) {
        this.t = t;
    }

    public ValueWrapper getW() {
        return w;
    }

    public void setW(ValueWrapper w) {
        this.w = w;
    }

    public ValueWrapper getWg() {
        return wg;
    }

    public void setWg(ValueWrapper wg) {
        this.wg = wg;
    }
}