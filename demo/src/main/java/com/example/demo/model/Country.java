package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class Country {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer countryId;
	
	@Column(name="name", nullable = false)
	private String name;
	
	public Country() {
		super();
	}

	public Country(Integer id, String name) {
		super();
		this.countryId = id;
		this.name = name;
	}

	public Integer getId() {
		return countryId;
	}

	public void setId(Integer id) {
		this.countryId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
