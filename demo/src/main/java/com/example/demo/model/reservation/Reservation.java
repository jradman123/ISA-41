package com.example.demo.model.reservation;

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

import com.example.demo.model.users.RegisteredUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reservation")
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

	
	 @Column(name = "price", nullable = false)
     private Double price;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private RegisteredUser registeredUser;
	
	 @Column(name = "isCanceled", nullable = false)
     private Boolean isCanceled = false;


	@Column(name = "isReserved", nullable = false)
	private Boolean isReserved;


	@Column(name = "isDeleted",nullable = false)
	private boolean isDeleted;
	
	 public Reservation() {
		super();
	 }
	
	
	
	
	
	
	
	
	
}
