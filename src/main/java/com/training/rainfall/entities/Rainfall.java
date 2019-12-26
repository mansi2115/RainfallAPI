package com.training.rainfall.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


/**
 * @author mmajalka
 *
 */
@Entity
@Table(name = "rainfall",uniqueConstraints=@UniqueConstraint(columnNames={"month", "city_id"}))
public class Rainfall {

	@Id
	//@SequenceGenerator(name = "rainfall_id", initialValue = 1, sequenceName = "rainfallSeq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="month")
	private String month;
	
	@Column(name = "rain")
	private double rain;
	          
//	@Column(name = "year")
//	private int year;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="city_id", nullable = false)
	private City city;

	
	/**
	 * default constructor
	 */
	public Rainfall() {
		super();
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the rain
	 */
	public double getRain() {
		return rain;
	}

	/**
	 * @param rain the rain to set
	 */
	public void setRain(double rain) {
		this.rain = rain;
	}

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}

	
	
	
}
	
