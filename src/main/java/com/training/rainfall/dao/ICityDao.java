package com.training.rainfall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.rainfall.entities.City;

@Repository
public interface ICityDao extends JpaRepository<City, Integer>{

	City findByCityName(String cityName);
}
