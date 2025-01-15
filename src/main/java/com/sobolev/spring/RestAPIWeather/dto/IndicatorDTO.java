package com.sobolev.spring.RestAPIWeather.dto;

import com.sobolev.spring.RestAPIWeather.models.Sensor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class IndicatorDTO {
    @Min(value = -100, message = "Value should be greater then -100.01")
    @Max(value = 100, message = "Value should be smaller then 100.01")
    @NotNull(message = "Value shouldn`t be empty")
    private Double value;

    @NotNull(message = "Raining should be true or false")
    private Boolean raining;

    private SensorDTO sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Boolean getRaining() {
        return raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "IndicatorDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                '}';
    }
}
