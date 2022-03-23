package com.example.demo.model;

import java.util.Set;

import com.example.demo.model.enumeration.EquipmentType;

public class Ship {
	
	private String name;
	private String type;
	private Double length;
	private String numberOfEngine;
	private Integer strengthOfEngine;
	private Integer maxSpeed;
	private EquipmentType equipment;
	private Address address;
	private String description;
	private Set<String> images;
	private Integer capacity;
	//slobodni termini za brze rezervacije
	private Set<String> rules;
	private String fishingEquipment;
	//cjenovnik i informacije o dodatnim uslugama
	private Double cancelationConditions;// 0% for free
	
	
	public Ship() {
		super();
	}


	public Ship(String name, String type, Double length, String numberOfEngine, Integer strengthOfEngine,
			Integer maxSpeed, EquipmentType equipment, Address address, String description, Set<String> images,
			Integer capacity, Set<String> rules, String fishingEquipment, Double cancelationConditions) {
		super();
		this.name = name;
		this.type = type;
		this.length = length;
		this.numberOfEngine = numberOfEngine;
		this.strengthOfEngine = strengthOfEngine;
		this.maxSpeed = maxSpeed;
		this.equipment = equipment;
		this.address = address;
		this.description = description;
		this.images = images;
		this.capacity = capacity;
		this.rules = rules;
		this.fishingEquipment = fishingEquipment;
		this.cancelationConditions = cancelationConditions;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Double getLength() {
		return length;
	}


	public void setLength(Double length) {
		this.length = length;
	}


	public String getNumberOfEngine() {
		return numberOfEngine;
	}


	public void setNumberOfEngine(String numberOfEngine) {
		this.numberOfEngine = numberOfEngine;
	}


	public Integer getStrengthOfEngine() {
		return strengthOfEngine;
	}


	public void setStrengthOfEngine(Integer strengthOfEngine) {
		this.strengthOfEngine = strengthOfEngine;
	}


	public Integer getMaxSpeed() {
		return maxSpeed;
	}


	public void setMaxSpeed(Integer maxSpeed) {
		this.maxSpeed = maxSpeed;
	}


	public EquipmentType getEquipment() {
		return equipment;
	}


	public void setEquipment(EquipmentType equipment) {
		this.equipment = equipment;
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


	public Integer getCapacity() {
		return capacity;
	}


	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
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
	
	
	
	
	
	

}
