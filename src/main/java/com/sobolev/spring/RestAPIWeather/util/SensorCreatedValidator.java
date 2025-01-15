package com.sobolev.spring.RestAPIWeather.util;

import com.sobolev.spring.RestAPIWeather.models.Sensor;
import com.sobolev.spring.RestAPIWeather.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorCreatedValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorCreatedValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if(sensorService.findByName(sensor.getName()) != null) {
            errors.rejectValue("name", null, "This name is already in use");
        }
    }
}
