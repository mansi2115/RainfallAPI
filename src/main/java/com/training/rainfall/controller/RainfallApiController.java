package com.training.rainfall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.rainfall.entities.City;
import com.training.rainfall.entities.Rainfall;
import com.training.rainfall.service.IRainfallService;

@RestController
@RequestMapping("/weather")
public class RainfallApiController {

	@Autowired
	IRainfallService service;

	// add city
	@PostMapping(value = "/setCity")
	public ResponseEntity<Object> setCity(@RequestBody City city) {

		return new ResponseEntity<Object>(service.setCity(city), HttpStatus.OK);

	}

	// add rainfall
	@PostMapping(value = "/setRainfall")
	public ResponseEntity<Object> setRainfall(@RequestBody Rainfall rainfall) {

		return new ResponseEntity<Object>(service.setRainfall(rainfall), HttpStatus.OK);

	}
	/*
	 * get all cities
	 */
	@GetMapping(value = "/getCities")
	public ResponseEntity<Object> getCities() {

		List<City> cities=service.getCities();
		return new ResponseEntity<Object>(cities, HttpStatus.OK);

	}
	/*
	 * get rainfall of given city for all months
	 */
	@GetMapping(value = "/getRainfallByCity")
	public ResponseEntity<Object> getRainfallByCity(@RequestParam("city") String city) {

		List<Rainfall> rain=service.getRainfallByCity(city);
		return new ResponseEntity<Object>(rain, HttpStatus.OK);

	}
	
	
	@GetMapping(value = "/getMonthlyAvgByCity")
	public ResponseEntity<Object> getMonthlyAvgByCity(@RequestParam String month,@RequestParam String city,@RequestParam String units)
	{
		return new ResponseEntity<Object>(month+" Average of "+city+" :"+service.getMonthlyAvgByCity(month, city, units), HttpStatus.OK);

	}
	
	@GetMapping(value = "/getYearlyAvgByCity")
	public ResponseEntity<Object> getYearlyAvgByCity(@RequestParam String city,@RequestParam String units)
	{
		return new ResponseEntity<Object>("Yearly Average of "+city+" :"+service.getYearlyAvgByCity(city, units), HttpStatus.OK);

	}
	
	@DeleteMapping(value = "/deleteCity")
	public ResponseEntity<Object> deleteCity(@RequestParam String city)
	{
		return new ResponseEntity<Object>(service.deleteCity(city), HttpStatus.OK);
	}
	

}
