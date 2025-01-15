package com.sobolev.spring.RestAPIWeather.controllers;

import com.sobolev.spring.RestAPIWeather.dto.IndicatorDTO;
import com.sobolev.spring.RestAPIWeather.models.Indicator;
import com.sobolev.spring.RestAPIWeather.services.IndicatorService;
import com.sobolev.spring.RestAPIWeather.util.MeasurementErrorResponse;
import com.sobolev.spring.RestAPIWeather.util.MeasurementsNotAdded;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addIndicator(@RequestBody @Valid IndicatorDTO indicatorDTO,
                                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for(FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new MeasurementsNotAdded(errorMsg.toString());
        }
        indicatorService.save(convertToIndicator(indicatorDTO)); // TODO

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private IndicatorDTO convertToIndicatorDTO(Indicator indicator) {
        return modelMapper.map(indicator, IndicatorDTO.class);
    }

    private Indicator convertToIndicator(IndicatorDTO indicatorDTO) {
        return modelMapper.map(indicatorDTO, Indicator.class);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handleException(MeasurementsNotAdded ex) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                ex.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
