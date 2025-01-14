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

    @Autowired
    public IndicatorService(IndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

    public List<Indicator> findAll() {
        return indicatorRepository.findAll();
    }
}
