package com.example.demo.model.reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import com.example.demo.model.Report;
import com.example.demo.model.cottages.CottageUtility;
import com.example.demo.model.ships.ShipUtility;
import com.example.demo.model.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reservation")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Reservation {
	
	 @Id
	 @Column(name = "id")
	 @SequenceGenerator(name = "mySeqGenV1", sequenceName = "mySeqV1", initialValue = 1, allocationSize = 1)
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV1")
	 private Long id;

	@OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
	@JsonBackReference
	private Report report;
	
     @Column(name = "reservationStart", nullable = false)
     private LocalDateTime reservationStart;

	 @Column(name = "reservationEnd", nullable = false)
     private LocalDateTime reservationEnd;

     @Column(name = "numberOfPerson", nullable = false)
	 private Integer numberOfPerson;

	
	 @Column(name = "price", nullable = false)
     private Double price;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User registeredUser;
	
	 @Column(name = "isCanceled", nullable = false)
     private Boolean isCanceled = false;



	@Column(name = "isReserved", nullable = false)
	private Boolean isReserved;


	@Column(name = "isDeleted",nullable = false)
	private boolean isDeleted;

	@Column(name = "haveReport",nullable = false)
	private boolean haveReport=false;

	@Column(name = "type",nullable = false)
	private String type;


	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
			name = "cottage_reservation_cottage_utility",
			joinColumns = @JoinColumn(name = "cottage_reservation_id"),
			inverseJoinColumns = @JoinColumn(name = "cottage_utility_id"))
	private Set<CottageUtility> cottageUtilities;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
			name = "ship_reservation_ship_utility",
			joinColumns = @JoinColumn(name = "ship_reservation_id"),
			inverseJoinColumns = @JoinColumn(name = "ship_utility_id"))
	private Set<ShipUtility> shipUtilities;
	
	 public Reservation() {
		super();
	 }









}
