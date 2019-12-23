package com.training.rainfall.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="City",uniqueConstraints=@UniqueConstraint(columnNames={"city_name"}))
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="city_id")
	private int cityId;
	
	@Column(name="city_name")
	private String cityName;

	/**
	 * default constructor
	 */
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	
}
