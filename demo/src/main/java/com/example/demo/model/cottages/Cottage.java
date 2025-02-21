package com.example.demo.model.cottages;

import java.util.Set;

import javax.persistence.*;

import com.example.demo.model.Address;
import com.example.demo.model.Image;
import com.example.demo.model.Rules;
import com.example.demo.model.reservation.Appointment;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.RegisteredUser;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(
			name = "cottage_images",
			joinColumns = @JoinColumn(name = "cottage_id"),
			inverseJoinColumns = @JoinColumn(name = "image_id"))
	private Set<Image> images;


	@Column(name = "numberOfPerson", nullable = false)
	private Integer numberOfPerson;

	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;


	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "cottage_id")
	@JsonManagedReference
	private Set<Rules> rules;

	 @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	 @JoinColumn(name = "cottage_id")
	 @JsonManagedReference
	 private Set<CottageUtility> utilities;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "cottage_id")
	@JsonManagedReference
	private Set<Appointment> cottageAppointments;



	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "cottage_id")
	@JsonManagedReference
	private Set<Room> rooms;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "cottage_id")
	@JsonManagedReference
	private Set<CottageAvailability> availabilities;

	@Column(name = "cancelationConditions", nullable = false)
	private Integer cancelationConditions;// 0% for free





	@OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
	    private Set<CottageReservation> cottageReservations;
	  
	  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	    @JoinColumn(name = "cottageOwner")
	    private CottageOwner cottageOwner;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "cottage_users",
			joinColumns = @JoinColumn(name = "cottage_id"),
			inverseJoinColumns = @JoinColumn(name = "users_id"))
	private Set<RegisteredUser> subscribers;

	public Cottage(String name, String description, Double price, Address address,CottageOwner owner,int numberOfPerson,Integer cancelationConditions,Set<RegisteredUser> subscribers) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.address = address;
		this.numberOfPerson=numberOfPerson;
		this.cottageOwner=owner;
		this.cancelationConditions=cancelationConditions;
		this.subscribers=subscribers;


	}




	}



