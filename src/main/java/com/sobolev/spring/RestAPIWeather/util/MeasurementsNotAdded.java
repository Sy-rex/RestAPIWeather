package com.sobolev.spring.RestAPIWeather.util;

public class MeasurementsNotAdded extends RuntimeException {
    public MeasurementsNotAdded(String message) {
        super(message);
    }
}
