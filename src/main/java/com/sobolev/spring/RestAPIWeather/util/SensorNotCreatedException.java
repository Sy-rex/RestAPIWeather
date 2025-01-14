package com.sobolev.spring.RestAPIWeather.util;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String message){
        super(message);
    }
}
