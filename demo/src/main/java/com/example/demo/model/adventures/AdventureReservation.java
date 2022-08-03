package com.example.demo.model.adventures;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.users.RegisteredUser;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "adventureReservation")
@PrimaryKeyJoinColumn(name = "id")
public class AdventureReservation extends Reservation {
	
	   @ManyToOne
	    @JoinColumn(name = "adventure_id")
	    private Adventure adventure;

	    @Column(name = "instructor_id")
	    private Long instructorId;

	   @ManyToMany
	   @JoinTable(
	            name = "adventure_reservation_utilities",
	            joinColumns = @JoinColumn(name = "adventure_reservation_id"),
	            inverseJoinColumns = @JoinColumn(name = "adventure_utility_id"))
	    private Set<AdventureUtility> utilities;
	





	
	
	

}
