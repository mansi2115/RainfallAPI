package com.training.rainfall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.rainfall.entities.City;
import com.training.rainfall.entities.Rainfall;
import com.training.rainfall.exception.RequestResponse;
import com.training.rainfall.service.IRainfallService;

@RestController
@RequestMapping("/weather")
public class RainfallApiController {

	@Autowired
	IRainfallService service;

	// add city
	@PostMapping(value = "/city")
	public ResponseEntity<Object> setCity(@RequestBody City city) {

		return new ResponseEntity<Object>(service.setCity(city), HttpStatus.OK);

	}

	// add rainfall
	@PostMapping(value = "/rainfall")
	public ResponseEntity<Object> setRainfall(@RequestBody Rainfall rainfall) {

		return new ResponseEntity<Object>(service.setRainfall(rainfall), HttpStatus.OK);

	}
	/*
	 * get all cities
	 */
	@GetMapping(value = "/city")
	public ResponseEntity<RequestResponse> getCities() {

		List<City> cities=service.getCities();
		RequestResponse result = new RequestResponse(true,cities, HttpStatus.OK.getReasonPhrase());
		return new ResponseEntity<RequestResponse>(result,HttpStatus.OK);

	}
	/*
	 * get rainfall of given city for all months
	 */
	@GetMapping(value = "/rainfall")
	public ResponseEntity<RequestResponse> getRainfallByCity(@RequestParam("city") String city) {

		List<Rainfall> rain=service.getRainfallByCity(city);
		RequestResponse result = new RequestResponse(true,rain, HttpStatus.OK.getReasonPhrase());
		return new ResponseEntity<RequestResponse>(result, HttpStatus.OK);

	}
	
	
	@GetMapping(value = "/getMonthlyAvgByCity")
	public ResponseEntity<RequestResponse> getMonthlyAvgByCity(@RequestParam String month,@RequestParam String city,@RequestParam String units)
	{
		RequestResponse result = new RequestResponse(true,service.getMonthlyAvgByCity(month, city, units), HttpStatus.OK.getReasonPhrase());
		return new ResponseEntity<RequestResponse>(result, HttpStatus.OK);

	}
	
	@GetMapping(value = "/getYearlyAvgByCity")
	public ResponseEntity<Object> getYearlyAvgByCity(@RequestParam String city,@RequestParam String units)
	{
		RequestResponse result = new RequestResponse(true,"Yearly Average of "+city+" :"+service.getYearlyAvgByCity(city, units), HttpStatus.OK.getReasonPhrase());

		return new ResponseEntity<Object>(result, HttpStatus.OK);

	}
	
	@DeleteMapping(value = "/city")
	public ResponseEntity<Object> deleteCity(@RequestParam String city)
	{
		RequestResponse result = new RequestResponse(true,service.deleteCity(city), HttpStatus.OK.getReasonPhrase());

		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/rainfall")
    public ResponseEntity<Object> deleteRainfall(@RequestParam String city,@RequestParam String month)
    {
        return new ResponseEntity<Object>(service.deleteRainfall(city, month), HttpStatus.OK);
    }
   
    @PutMapping(value = "/rainfall")
    public ResponseEntity<Object> updateRainfall(@RequestParam String city,@RequestParam String month,@RequestParam double rain)
    {
        return new ResponseEntity<Object>(service.updateRainfall(city, month, rain), HttpStatus.OK);
    }
	

}
