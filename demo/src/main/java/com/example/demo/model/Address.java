package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="address")
public class Address {

	 @Id
	 @Column(name = "id")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 @Column(name="streetName", nullable = false)
	 private String streetName;
	 
	 @Column(name="streetNumber", nullable = false)
	 private String streetNumber;
	 
	 @Column(name="city", nullable = false)
	 private String city;
	 
	 @Column(name="country", nullable = false)
     private String country;

	@Column(name = "longitude", unique = false, nullable = false)
	private double longitude;

	@Column(name = "latitude", unique = false, nullable = false)
	private double latitude;
	
	public Address() {
		super();
	}

	public Address(String streetName, String streetNumber, String city, String country,double longitude,double latitude) {
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
		this.country = country;
		this.longitude=longitude;
		this.latitude=latitude;

	}

	@Override
	    public String toString() {
	        return streetName + ", " + streetNumber + ", " + city + ", " + country;
	    }

	
	
}