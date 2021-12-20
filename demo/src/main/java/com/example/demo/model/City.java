package com.example.demo.model;


public class City {
	
	private String id;
	private String name;
	private String postalCode;
	private Country country;
	
	
	public City() {
		super();
	}

	public City(String id, String name, String postalCode, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.postalCode = postalCode;
		this.country = country;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
