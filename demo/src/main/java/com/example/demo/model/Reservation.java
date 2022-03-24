package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reservation")
//@Inheritance(strategy = InheritanceType.JOINED)
public class Reservation {
	
	 @Id
	 @Column(name = "id")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
     @Column(name = "reservationStart", nullable = false)
     private LocalDateTime reservationStart;

	 @Column(name = "reservationEnd", nullable = false)
     private LocalDateTime reservationEnd;

     @Column(name = "numberOfPerson", nullable = false)
	 private Integer numberOfPerson;
    
//	private Set<String> services;
	
	 @Column(name = "price", nullable = false)
     private Double price;

	
	 @Column(name = "isCanceled", nullable = false)
     private Boolean isCanceled = false;
	
	 @ManyToOne
     @JoinColumn(name = "quickReservation_id", nullable = true)
	 private QuickReservation quickReservation;
	
	
	
	 public Reservation() {
		super();
	 }
	
	
	
	
	
	
	
	
	
}
