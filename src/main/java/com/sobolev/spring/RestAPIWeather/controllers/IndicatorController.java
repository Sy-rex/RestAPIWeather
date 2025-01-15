package com.sobolev.spring.RestAPIWeather.controllers;

import com.sobolev.spring.RestAPIWeather.dto.IndicatorDTO;
import com.sobolev.spring.RestAPIWeather.models.Indicator;
import com.sobolev.spring.RestAPIWeather.services.IndicatorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class IndicatorController {

    private final IndicatorService indicatorService;
    private final ModelMapper modelMapper;

    @Autowired
    public IndicatorController(IndicatorService indicatorService, ModelMapper modelMapper) {
        this.indicatorService = indicatorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<IndicatorDTO> getIndicators() {
        return indicatorService.findAll().stream().map(this::convertToIndicatorDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public Integer getRainyDaysCount() {
        return indicatorService.getRainyDaysCount();
    }

    private IndicatorDTO convertToIndicatorDTO(Indicator indicator) {
        return modelMapper.map(indicator, IndicatorDTO.class);
    }
}
