package com.sobolev.spring.RestAPIWeather.util;

import com.sobolev.spring.RestAPIWeather.models.Indicator;
import com.sobolev.spring.RestAPIWeather.models.Sensor;
import com.sobolev.spring.RestAPIWeather.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorSearchValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorSearchValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Indicator indicator = (Indicator) target;

        if(sensorService.findByName(indicator.getSensor().getName()) == null){
            errors.rejectValue("sensor", "sensor.not.found", "This name of the sensor does not exist");
        }
    }
}
