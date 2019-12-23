package com.training.rainfall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.rainfall.dao.ICityDao;
import com.training.rainfall.dao.IRainfallRepository;
import com.training.rainfall.entities.City;
import com.training.rainfall.entities.Rainfall;
import com.training.rainfall.exception.InvalidUnitException;

@Service
public class RainfallServiceImpl implements IRainfallService{

	@Autowired
	IRainfallRepository  rainfallDao;
	@Autowired
	ICityDao cityDao;
	
	@Override
	public City setCity(City city) {
		
		return cityDao.save(city);
	}

	@Override
	public Rainfall setRainfall(Rainfall rainfall) {
		City city= cityDao.findByCityName(rainfall.getCity().getCityName());
		rainfall.setCity(city);
		return rainfallDao.save(rainfall);
	
	}
	
	@Override
	public List<City> getCities(){
		List<City> cities=cityDao.findAll();
		return cities;
	}

	@Override
	public double getMonthlyAvgByCity(String month, String city, String units) {
		double avg;
		if(units.equalsIgnoreCase("mm")) {
			avg = rainfallDao.findMonthlyAvgRainfallByCity(month, cityDao.findByCityName(city).getCityId());
			return avg;
		}
		else if(units.equalsIgnoreCase("cm")) {
			avg = rainfallDao.findMonthlyAvgRainfallByCity(month, cityDao.findByCityName(city).getCityId());
			return convertToCm(avg);
		}
		else {
			 throw new InvalidUnitException("Invalid unit");
		}
		
	}

	@Override
	public double getYearlyAvgByCity(String city, String units) {
		double avg;
		if(units.equalsIgnoreCase("mm")) {
			avg = rainfallDao.findYearlyAvgRainfallByCity(cityDao.findByCityName(city).getCityId());
			return avg;
		}
		else if(units.equalsIgnoreCase("cm")) {
			avg = rainfallDao.findYearlyAvgRainfallByCity(cityDao.findByCityName(city).getCityId());
			return convertToCm(avg);
		}
		else {
			 throw new InvalidUnitException("Invalid unit");
		}
	}

	@Override
	public double convertToCm(double mm) {
		
		return mm/10;
	}

	@Override
	public List<Rainfall> getRainfallByCity(String cityName) {
		int id=cityDao.findByCityName(cityName).getCityId();
		List<Rainfall> rain=rainfallDao.findRainfallByCity(id);
		return rain;	
	}

	@Override
	public boolean deleteCity(String cityName) {
		int id=cityDao.findByCityName(cityName).getCityId();
		cityDao.deleteById(id);
		if(cityDao.existsById(id)) {
			return false;
		}
		else {
			return true;
		}
		
	}

	
	
}
