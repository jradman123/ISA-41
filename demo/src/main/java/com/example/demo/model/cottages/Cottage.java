package com.example.demo.model.cottages;

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

import com.example.demo.model.Address;
import com.example.demo.model.Image;
import com.example.demo.model.Rules;
import com.example.demo.model.adventures.AdventureQuickReservation;
import com.example.demo.model.adventures.AdventureReservation;
import com.example.demo.model.users.CottageOwner;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "cottage")
public class Cottage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "name", nullable = false, unique = true)
	private String name;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address", referencedColumnName = "id")
	private Address address;
    
    @Column(name = "description", nullable = false)
	private String description;
    
    @Column(name = "price", nullable = false)
    private Double price = 0.0;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cottage_images",
            joinColumns = @JoinColumn(name = "cottage_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images= new HashSet<>();
	
	@Column(name = "numberOfPerson", nullable = false)
	private Integer numberOfPerson;
	
	
	@ManyToMany
	    @JoinTable(
	            name = "cottage_rules",
	            joinColumns = @JoinColumn(name = "cottage_id"),
	            inverseJoinColumns = @JoinColumn(name = "rule_id"))
	private Set<Rules> rules;
	
	 @OneToMany
	    @JoinColumn(name = "cottage_id")
	    @JsonManagedReference
	    private Set<CottageUtility> utilities;
	
	
	 
	 @OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
	    private Set<CottageQuickReservation> cottageQuickReservations;

	  @OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
	    private Set<CottageReservation> cottageReservations;
	  
	  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	    @JoinColumn(name = "cottageOwner")
	    private CottageOwner cottageOwner;

	/*

	 @OneToMany(mappedBy = "cottage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    @JsonManagedReference
	    private Set<Room> rooms;
	*/
}