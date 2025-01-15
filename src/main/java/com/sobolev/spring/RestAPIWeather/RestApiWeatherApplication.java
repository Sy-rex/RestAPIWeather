package com.sobolev.spring.RestAPIWeather;

import com.sobolev.spring.RestAPIWeather.dto.IndicatorDTO;
import com.sobolev.spring.RestAPIWeather.models.Indicator;
import org.modelmapper.ModelMapper;
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
		modelMapper.typeMap(Indicator.class, IndicatorDTO.class)
				.addMapping(src -> src.getSensor().getName(), IndicatorDTO::setSensorName);
		return modelMapper;
	}
}
