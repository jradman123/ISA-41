package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	

	public Address(String streetName, String streetNumber, String city, String country) {
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
		this.country = country;
	}

	@Override
	    public String toString() {
	        return streetName + ", " + streetNumber + ", " + city + ", " + country;
	    }

	
	
}