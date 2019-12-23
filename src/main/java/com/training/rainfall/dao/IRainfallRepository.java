package com.training.rainfall.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.rainfall.entities.Rainfall;

public interface IRainfallRepository extends JpaRepository<Rainfall,Integer> {
	
	@Query("select avg(r.rain) from Rainfall r where city_id =?1")
	double findYearlyAvgRainfallByCity(int cityId);
	
	@Query("select r.rain from Rainfall r where month=?1 and city_id=?2")
	double findMonthlyAvgRainfallByCity(String month,int cityId);
	
	
	@Query("select r from Rainfall r where city_id =?1")
	List<Rainfall> findRainfallByCity(int id);
	

}
