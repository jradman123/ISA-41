package com.example.demo.model;

import java.util.Set;

public class Cottage {

	private String name;
	private Address address;
	private String description;
	private Set<String> images;
	private Integer numberOfRooms;
	private Integer numberOfBedsPerRoom;
	private Set<String> rules;
	//slobodni termini za brze rezervacije
	
	
	
	public Cottage(String name, Address address, String description, Set<String> images, Integer numberOfRooms,
			Integer numberOfBedsPerRoom, Set<String> rules) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.images = images;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBedsPerRoom = numberOfBedsPerRoom;
		this.rules = rules;
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
	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public Integer getNumberOfBedsPerRoom() {
		return numberOfBedsPerRoom;
	}
	public void setNumberOfBedsPerRoom(Integer numberOfBedsPerRoom) {
		this.numberOfBedsPerRoom = numberOfBedsPerRoom;
	}
	public Set<String> getRules() {
		return rules;
	}
	public void setRules(Set<String> rules) {
		this.rules = rules;
	}
	public Cottage() {
		super();
	}
	
	
	
	
	
	
	
	
}
