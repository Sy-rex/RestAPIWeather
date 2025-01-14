package com.sobolev.spring.RestAPIWeather.repositories;

import com.sobolev.spring.RestAPIWeather.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
}
