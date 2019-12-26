package com.training.rainfall.service;


import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.rainfall.dao.ICityDao;
import com.training.rainfall.dao.IRainfallRepository;
import com.training.rainfall.entities.City;
import com.training.rainfall.entities.Rainfall;
import com.training.rainfall.exception.InvalidInputException;

@Transactional
@Service
public class RainfallServiceImpl implements IRainfallService{

	@Autowired
	IRainfallRepository  rainfallDao;
	@Autowired
	ICityDao cityDao;
	
	
	
	@Override
	public City setCity(City city) {
		if(cityDao.findByCityName(city.getCityName())!=null) {
			throw new InvalidInputException(city.getCityName()+" already exist ");
		}
		else {
			double latitude=new Random().nextDouble();
			double longitude=new Random().nextDouble();
			city.setLatitude(latitude);
			city.setLongitude(longitude);
			return cityDao.save(city);
		}
		
	}

	@Override
	public Rainfall setRainfall(Rainfall rainfall) {
		City city= cityDao.findByCityName(rainfall.getCity().getCityName());
		if(city == null) {
        	throw new InvalidInputException("City does not exist. Please add city first.");
        }
		Rainfall rain = rainfallDao.findRainfall(rainfall.getMonth(), city.getCityId());
		if(rain!=null) {
        	throw new InvalidInputException("Already exists");
        }
			rainfall.setCity(city);
			return rainfallDao.save(rainfall);
	}
	
	@Override
	public List<City> getCities(){
		List<City> cities=cityDao.findAll();
		return cities;
	}

	@Override
	public double getMonthlyAvgByCity(String month, String cityName, String units) {
		
		City city=cityDao.findByCityName(cityName);

		if(city == null) {
        	throw new InvalidInputException("City does not exist");
        }
		Rainfall rain = rainfallDao.findRainfall(month, city.getCityId());
		if(rain==null) {
        	throw new InvalidInputException("Given Rainfall is Not Found");
        }
		else {
			if(units.equalsIgnoreCase("mm")) {
				
				return rain.getRain();
			}
			else if(units.equalsIgnoreCase("cm")) {
				return convertToCm(rain.getRain());
			}
			else {
				 throw new InvalidInputException("Invalid unit");
			}
			
		}
		
	}

	@Override
	public double getYearlyAvgByCity(String cityName, String units) {
		double avg;
		City city=cityDao.findByCityName(cityName);
		if(city == null) {
        	throw new InvalidInputException("City does not exist");
        }
		avg=rainfallDao.findYearlyAvgRainfallByCity(city.getCityId());
		if(units.equalsIgnoreCase("mm")) {
			
			return avg;
		}
		else if(units.equalsIgnoreCase("cm")) {

			return convertToCm(avg);
		}
		else {
			 throw new InvalidInputException("Invalid unit");
		}
	}

	@Override
	public double convertToCm(double mm) {
		
		return mm/10;
	}

	@Override
	public List<Rainfall> getRainfallByCity(String cityName) {
		City city=cityDao.findByCityName(cityName);
		if(city == null) {
        	throw new InvalidInputException("City does not exist");
        }
		List<Rainfall> rain=rainfallDao.findRainfallByCity(city.getCityId());
		return rain;	
	}

	@Override
	public String deleteCity(String cityName) {
		City city=cityDao.findByCityName(cityName);
		
        if(city == null) {
        	throw new InvalidInputException("City does not exist");
        }
        else {
        	cityDao.delete(city);
        	return "Deleted Succefully";
        }
        
		
	}
	
	@Override
    public String deleteRainfall(String cityName,String month) {
        City city=cityDao.findByCityName(cityName);
        if(city == null) {
        	throw new InvalidInputException("City does not exist");
        }
        Rainfall rain=rainfallDao.findRainfall(month,city.getCityId());
        if(rain==null) {
        	throw new InvalidInputException("Given Rainfall is Not Found");
        }
        else {
        	rainfallDao.delete(rain);
        	return "Deleted Succefully";
        }
        
    }
	
	 @Override
	    public String updateRainfall(String cityName, String month, double rain) {
	        City city=cityDao.findByCityName(cityName);
	        if(city == null) {
	        	throw new InvalidInputException("City does not exist");
	        }
	        else if(rainfallDao.findRainfall(month,city.getCityId())==null) {
	        	throw new InvalidInputException("Given rainfall does not exist");

	        }
	        else {
	        	 int i=   rainfallDao.updateRainfall(rain,city.getCityId(), month);
	    	     if(i==1) {
	    	    	 return "Updated Succesfully";
	    	     }
	 	        
	    	     return "Updation failed. Please check your data";
	        }
	        }

	
	
}
