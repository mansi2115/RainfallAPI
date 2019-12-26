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
		System.out.println(city);
		//System.out.println();
		//try {
			//int id=rainfallDao.findidRainfall(city.getCityId(),rainfall.getMonth());
		//System.out.println(rainfallDao.findidRainfall(city.getCityId(),rainfall.getMonth()));
//		if(rainfallDao.existsById()) {
//			throw new InvalidInputException("already exist ");
//		}
//		else
//		{
			rainfall.setCity(city);
			return rainfallDao.save(rainfall);
	//	}
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
			 throw new InvalidInputException("Invalid unit");
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
			 throw new InvalidInputException("Invalid unit");
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
	
	@Override
    public String deleteRainfall(String cityName,String month) {
        int id=cityDao.findByCityName(cityName).getCityId();
        int rainid=rainfallDao.findidRainfall(id, month);
        rainfallDao.deleteById(rainid);
        if(rainfallDao.existsById(rainid)) {
            return "Not Deleted";
        }
        else {
            return "Deleted";
        }
    
    }
	
	 @Override
	    public String updateRainfall(String cityName, String month, double rain) {
	        int id=cityDao.findByCityName(cityName).getCityId();
	        
	         rainfallDao.updateRainfall(rain, id, month);
	        return "updated";
	    }

	
	
}
