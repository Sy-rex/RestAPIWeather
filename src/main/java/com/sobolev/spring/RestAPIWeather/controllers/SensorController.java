package com.sobolev.spring.RestAPIWeather.controllers;

import com.sobolev.spring.RestAPIWeather.dto.SensorDTO;
import com.sobolev.spring.RestAPIWeather.models.Sensor;
import com.sobolev.spring.RestAPIWeather.services.SensorService;
import com.sobolev.spring.RestAPIWeather.util.SensorCreatedValidator;
import com.sobolev.spring.RestAPIWeather.util.SensorErrorResponse;
import com.sobolev.spring.RestAPIWeather.util.SensorNotCreatedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorCreatedValidator sensorCreatedValidator;

    @Autowired
    public SensorController(SensorService sensorService,
                            ModelMapper modelMapper,
                            SensorCreatedValidator sensorCreatedValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorCreatedValidator = sensorCreatedValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult) {
        sensorCreatedValidator.validate(convertToSensor(sensorDTO), bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(" \n");
            }

            throw new SensorNotCreatedException(errorMsg.toString());
        }

        sensorService.save(convertToSensor(sensorDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
