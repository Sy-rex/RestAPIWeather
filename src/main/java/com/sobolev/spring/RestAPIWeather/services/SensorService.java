package com.sobolev.spring.RestAPIWeather.services;

import com.sobolev.spring.RestAPIWeather.models.Sensor;
import com.sobolev.spring.RestAPIWeather.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Sensor findByName(String name) {
        return sensorRepository.findByName(name).orElse(null);
    }
}
