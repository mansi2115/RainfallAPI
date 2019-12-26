package com.training.rainfall.service;

import java.util.List;

import com.training.rainfall.entities.City;
import com.training.rainfall.entities.Rainfall;

public interface IRainfallService {

	
	City setCity(City city);
	Rainfall setRainfall(Rainfall rainfall);
	
	
	List<City> getCities();
	List<Rainfall> getRainfallByCity(String cityName);
	
	String deleteCity(String cityName);
	String deleteRainfall(String cityName, String month);
	
	double getMonthlyAvgByCity(String month,String city,String units);
	double getYearlyAvgByCity(String city,String units);
	
	double convertToCm(double mm);
	
	String updateRainfall(String cityName, String month, double rain);
	

	
	
	
	
}
