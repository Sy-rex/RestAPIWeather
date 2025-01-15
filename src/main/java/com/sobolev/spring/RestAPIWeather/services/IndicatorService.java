package com.sobolev.spring.RestAPIWeather.services;

import com.sobolev.spring.RestAPIWeather.models.Indicator;
import com.sobolev.spring.RestAPIWeather.repositories.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IndicatorService {
    private final IndicatorRepository indicatorRepository;
    private final SensorService sensorService;

    @Autowired
    public IndicatorService(IndicatorRepository indicatorRepository, SensorService sensorService) {
        this.indicatorRepository = indicatorRepository;
        this.sensorService = sensorService;
    }

    public List<Indicator> findAll() {
        List<Indicator> indicators = indicatorRepository.findAll();
        return indicatorRepository.findAll();
    }

    public Integer getRainyDaysCount() {
        return indicatorRepository.countAllRaining();
    }

    @Transactional
    public void save(Indicator indicator) {
        indicator.setSensor(sensorService.findByName(indicator.getSensor().getName()));

        indicatorRepository.save(indicator);
    }
}
