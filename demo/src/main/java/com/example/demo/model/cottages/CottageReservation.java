package com.example.demo.model.cottages;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.users.RegisteredUser;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cottageReservation")
@PrimaryKeyJoinColumn(name = "id")
public class CottageReservation extends Reservation {

	
	    @ManyToOne
	    @JoinColumn(name = "cottage_id")
	    private Cottage cottage;
	 
	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private RegisteredUser registeredUser;



}
