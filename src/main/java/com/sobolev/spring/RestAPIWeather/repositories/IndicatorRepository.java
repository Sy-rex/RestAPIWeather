package com.sobolev.spring.RestAPIWeather.repositories;

import com.sobolev.spring.RestAPIWeather.models.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Integer> {
}
