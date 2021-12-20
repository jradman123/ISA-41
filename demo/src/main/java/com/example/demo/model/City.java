package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cities")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cityId", unique = true, nullable = false)
	private Integer cityId;
	
	@Column(name = "city_name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "postal_code", unique = true, nullable = false)
	private String postalCode;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "countryId", referencedColumnName = "countryId")
	private Country country;
	
	
	public City() {
		super();
	}

	public City(Integer id, String name, String postalCode, Country country) {
		super();
		this.cityId = id;
		this.name = name;
		this.postalCode = postalCode;
	    this.country = country;
	}

	public Integer getId() {
		return cityId;
	}

	public void setId(Integer id) {
		this.cityId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	

}
