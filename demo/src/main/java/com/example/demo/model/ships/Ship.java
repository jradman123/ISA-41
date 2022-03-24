package com.example.demo.model.ships;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.model.Address;
import com.example.demo.model.enumeration.EquipmentType;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "ship")
public class Ship {
	
	 @Id
	 @Column(name = "id")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	@Column(name = "name", nullable = false)
    private String name;
	
	@Column(name = "shipType", nullable = false) 
	private String type;
	
	@Column(name = "shipLength", nullable = false)
	private Double length;
	
	@Column(name = "numberOfEngine")
	private String numberOfEngine;
	
	@Column(name = "strengthOfEngine", nullable = false)
	private Double strengthOfEngine;
	
	@Column(name = "maxSpeed", nullable = false)
	private Double maxSpeed;
	
	@Column(name = "price", nullable = false)
	private Double price = 0.0;
	
	
	//private EquipmentType equipment;
	
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address", referencedColumnName = "id")
	private Address address;
    
    @Column(name = "description", nullable = false)
    private String description;
    
/*
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "boat_images",
            joinColumns = @JoinColumn(name = "boat_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;
	*/
	@Column(name = "capacity", nullable = false)
	private Integer capacity;
	
	//slobodni termini za brze rezervacije
	//private Set<String> rules;
	
	private String fishingEquipment;
	//cjenovnik i informacije o dodatnim uslugama
	private Double cancelationConditions;// 0% for free
	
	 @Column(name = "deleted")
	 private boolean deleted = false;
	
}