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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.model.Address;
import com.example.demo.model.Rules;
import com.example.demo.model.enumeration.EquipmentType;
import com.example.demo.model.users.ShipOwner;

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
	
	
	 @OneToMany
	 @JoinColumn(name = "ship_id")
	 private Set<ShipUtility> utilities;
	
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
	
	 @OneToMany(mappedBy = "ship")
	 private Set<ShipQuickReservation> shipQuickReservations;

	 @OneToMany(mappedBy = "ship")
	 private Set<ShipReservation> shipReservations;
	 
	 @ManyToMany
	    @JoinTable(
	            name = "ship_rules",
	            joinColumns = @JoinColumn(name = "ship_id"),
	            inverseJoinColumns = @JoinColumn(name = "rule_id"))
	 private Set<Rules> rules;
	
    @Column(name = "fishingEquipment", nullable = false)
	private String fishingEquipment;
    
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ship_navigational_equipment",
            joinColumns = @JoinColumn(name = "ship_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Set<NavigationalEquipment> navigationalEquipments;
    
    
    @Column(name = "cancelationConditions", nullable = false)
	private Double cancelationConditions;// 0% for free
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "shipOwner")
    private ShipOwner shipOwner;
	
	 @Column(name = "deleted")
	 private boolean deleted = false;
	
}