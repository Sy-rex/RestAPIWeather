package com.sobolev.spring.RestAPIWeather.dto;

import com.sobolev.spring.RestAPIWeather.models.Sensor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class IndicatorDTO {
    @Min(value = -100, message = "Value should be greater then -100.01")
    @Max(value = 100, message = "Value should be smaller then 100.01")
    private double value;

    @NotEmpty(message = "Raining should be true or false")
    private boolean raining;

    private String sensorName;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public boolean getRaining() {
        return raining;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    @Override
    public String toString() {
        return "IndicatorDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensorName +
                '}';
    }
}
