package com.example.demo.model;

import java.util.Set;

public class Adventure {

	private Integer id;
	private Integer instructorId;
	private String name;
	private Address address;
	private String description;
	private Set<String> images;
	//slobodni termini
	private Integer maxPersons;
	private Set<String> rules;
	private String fishingEquipment;
	private Double cancelationConditions;
	private Double price;
	

	public Adventure() {
		super();
	}

	public Adventure(Integer id, Integer instructorId, String name, Address address, String description,
			Set<String> images, Integer maxPersons, Set<String> rules, String fishingEquipment,
			Double cancelationConditions, Double price) {
		super();
		this.id = id;
		this.instructorId = instructorId;
		this.name = name;
		this.address = address;
		this.description = description;
		this.images = images;
		this.maxPersons = maxPersons;
		this.rules = rules;
		this.fishingEquipment = fishingEquipment;
		this.cancelationConditions = cancelationConditions;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getImages() {
		return images;
	}

	public void setImages(Set<String> images) {
		this.images = images;
	}

	public Integer getMaxPersons() {
		return maxPersons;
	}

	public void setMaxPersons(Integer maxPersons) {
		this.maxPersons = maxPersons;
	}

	public Set<String> getRules() {
		return rules;
	}

	public void setRules(Set<String> rules) {
		this.rules = rules;
	}

	public String getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(String fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}

	public Double getCancelationConditions() {
		return cancelationConditions;
	}

	public void setCancelationConditions(Double cancelationConditions) {
		this.cancelationConditions = cancelationConditions;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	
}
