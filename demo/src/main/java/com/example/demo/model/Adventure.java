package com.example.demo.model;

import java.awt.Image;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@Table(name = "adventure")
public class Adventure {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "instructor")
	private Instructor instructor;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address", referencedColumnName = "id")
	private Address address;
	
	@Column(name="description", nullable = false)
	private String description;
	
	/*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "adventure_images",
            joinColumns = @JoinColumn(name = "adventure_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
	private Set<Image> images;*/
	//slobodni termini
	
	
	 @Column(name = "guestLimit", nullable = false)
	 private Integer guestLimit;
	
	
	//private Set<String> rules;
	 
	 
	/* @ManyToMany(cascade = CascadeType.ALL)
	 @JoinTable(
	            name = "adventure_fishing_equipment",
	            joinColumns = @JoinColumn(name = "adventure_id"),
	            inverseJoinColumns = @JoinColumn(name = "equipment_id"))*/
	private String fishingEquipment;
	
	
	private Double cancelationConditions;
	
	
	@Column(name = "price", nullable = false)
    private Double price = 0.0;
	
	
	@Column(name = "deleted")
    private boolean deleted = false;
	

	public Adventure() {
		super();
	}

	

	public Adventure(Integer id, Instructor instructor, String name, Address address, String description,
			Integer guestLimit, String fishingEquipment, Double cancelationConditions, Double price, boolean deleted) {
		super();
		this.id = id;
		this.instructor = instructor;
		this.name = name;
		this.address = address;
		this.description = description;
		this.guestLimit = guestLimit;
		this.fishingEquipment = fishingEquipment;
		this.cancelationConditions = cancelationConditions;
		this.price = price;
		this.deleted = deleted;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

/*	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}*/

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

	/*public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}*/

	

	/*public Set<String> getRules() {
		return rules;
	}

	public void setRules(Set<String> rules) {
		this.rules = rules;
	}
*/
	public String getFishingEquipment() {
		return fishingEquipment;
	}

	public Instructor getInstructor() {
		return instructor;
	}



	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}



	public Integer getGuestLimit() {
		return guestLimit;
	}



	public void setGuestLimit(Integer guestLimit) {
		this.guestLimit = guestLimit;
	}



	public boolean isDeleted() {
		return deleted;
	}



	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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
