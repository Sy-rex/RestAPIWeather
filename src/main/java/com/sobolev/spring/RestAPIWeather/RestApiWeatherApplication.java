package com.sobolev.spring.RestAPIWeather;

import com.sobolev.spring.RestAPIWeather.dto.IndicatorDTO;
import com.sobolev.spring.RestAPIWeather.dto.SensorDTO;
import com.sobolev.spring.RestAPIWeather.models.Indicator;
import com.sobolev.spring.RestAPIWeather.models.Sensor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApiWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiWeatherApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

//		// Создаём пустую TypeMap для Indicator -> IndicatorDTO
//		TypeMap<Indicator, IndicatorDTO> typeMap = modelMapper.createTypeMap(Indicator.class, IndicatorDTO.class);

//		typeMap.addMappings(mapper ->
//				mapper.map(Indicator::getSensor, IndicatorDTO::setSensor)
//		);
//
//		modelMapper.createTypeMap(Sensor.class, SensorDTO.class)
//				.addMapping(Sensor::getName, SensorDTO::setName);

		return modelMapper;
	}
}
