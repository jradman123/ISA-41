package com.example.demo.model.adventures;

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
import com.example.demo.model.users.Instructor;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "adventure")
public class Adventure {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor")
	private Instructor instructor;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address", referencedColumnName = "id")
	private Address address;
	
	@Column(name="description", nullable = false)
	private String description;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "adventure_images",
            joinColumns = @JoinColumn(name = "adventure_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
	private Set<Image> images;



	@OneToMany(mappedBy = "adventure", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
	private Set<AdventureReservation> adventureReservations;

	@Column(name = "guestLimit", nullable = false)
	private Integer guestLimit;
	
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "adventure_rules",
			   joinColumns = @JoinColumn(name = "adventure_id"),
			   inverseJoinColumns = @JoinColumn(name = "rule_id"))
	private Set<Rules> rules;
	 
	 
	 @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 @JoinTable(
	            name = "adventure_fishing_equipment",
	            joinColumns = @JoinColumn(name = "adventure_id"),
	            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
	 private Set<FishingEquipment> fishingEquipments;
	
	 
    @Column(name = "cancellationConditions", nullable = false)
	private Double cancellationConditions;
	
	
	@Column(name = "price", nullable = false)
    private Double price;
	
	
	@Column(name = "deleted")
    private boolean deleted = false;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "adventure_id")
	private Set<AdventureUtility> utilities;
	

	public Adventure() {
		super();
	}

	


	public Adventure(Integer id, Instructor instructor, String name, Address address, String description,
			Integer guestLimit, Set<Rules> rules, Set<FishingEquipment> fishingEquipments, Double cancellationConditions,
			Double price, boolean deleted,Set<AdventureUtility> utilities) {
		super();
		this.id = id;
		this.instructor = instructor;
		this.name = name;
		this.address = address;
		this.description = description;
		this.guestLimit = guestLimit;
		this.rules = rules;
		this.fishingEquipments = fishingEquipments;
		this.cancellationConditions = cancellationConditions;
		this.price = price;
		this.deleted = deleted;
		this.utilities = utilities;
	}


}
