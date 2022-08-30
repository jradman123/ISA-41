package com.example.demo.model.ships;

import java.util.HashSet;
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

import com.example.demo.dto.ShipDto;
import com.example.demo.model.Address;
import com.example.demo.model.Image;
import com.example.demo.model.Rules;
import com.example.demo.model.enumeration.EquipmentType;
import com.example.demo.model.users.RegisteredUser;
import com.example.demo.model.users.ShipOwner;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ships")
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

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ship_id")
	@JsonManagedReference
	 private Set<ShipUtility> utilities;
	
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address", referencedColumnName = "id")
	private Address address;
    
    @Column(name = "description", nullable = false)
    private String description;

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(
			name = "ship_images",
			joinColumns = @JoinColumn(name = "ship_id"),
			inverseJoinColumns = @JoinColumn(name = "image_id"))
	private Set<Image> images;



	@Column(name = "capacity", nullable = false)
	private Integer capacity;
	

	 @OneToMany(mappedBy = "ship",fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
	 private Set<ShipReservation> shipReservations;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ship_id")
	@JsonManagedReference
	 private Set<Rules> rules;
	
    @Column(name = "fishingEquipment", nullable = false)
	private String fishingEquipment;


	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ship_id")
	@JsonManagedReference
	private Set<NavigationalEquipment> navigationalEquipments;
    
    
    @Column(name = "cancelationConditions", nullable = false)
	private Integer cancelationConditions;// 0% for free
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "shipOwner")
    private ShipOwner shipOwner;
	
	 @Column(name = "deleted")
	 private boolean deleted = false;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "ship_users",
			joinColumns = @JoinColumn(name = "ship_id"),
			inverseJoinColumns = @JoinColumn(name = "users_id"))
	private Set<RegisteredUser> subscribers;

	public Ship(String name, String description, double price, Address address, ShipOwner owner, int capacity, double maxSpeed, int cancelationConditions, double length, double strengthOfEngine, String fishingEquipment, String numberOfEngine, String type,Set<RegisteredUser> subscribers) {
	this.name=name;
	this.description=description;
	this.price=price;
	this.address=address;
	this.shipOwner=owner;
	this.capacity=capacity;
	this.maxSpeed=maxSpeed;
	this.cancelationConditions=cancelationConditions;
	this.length=length;
	this.strengthOfEngine=strengthOfEngine;
	this.fishingEquipment=fishingEquipment;
	this.numberOfEngine=numberOfEngine;
	this.type=type;
	this.subscribers=subscribers;

	}


}