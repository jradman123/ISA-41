package com.example.demo.model.cottages;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.demo.model.reservation.QuickReservation;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cottageQuickReservation")
@PrimaryKeyJoinColumn(name = "id")
public class CottageQuickReservation extends QuickReservation {

	   @ManyToOne
	    @JoinColumn(name = "cottage_id")
	    private Cottage cottage;
	 
	   ///klijent

	    @ManyToMany
	    @JoinTable(
	            name = "cottage_reservation_utilities",
	            joinColumns = @JoinColumn(name = "cottage_reservation_id"),
	            inverseJoinColumns = @JoinColumn(name = "cottage_utility_id"))
	    private Set<CottageUtility> utilities;
}
