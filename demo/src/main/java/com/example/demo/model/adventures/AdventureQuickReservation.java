package com.example.demo.model.adventures;

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
@Table(name = "adventureQuickReservation")
@PrimaryKeyJoinColumn(name = "id")
public class AdventureQuickReservation  extends QuickReservation{
	 
	  @ManyToMany
	    @JoinTable(
	            name = "adventure_quick_reservation_utilities",
	            joinColumns = @JoinColumn(name = "adventure_reservation_id"),
	            inverseJoinColumns = @JoinColumn(name = "adventure_utility_id"))
	    private Set<QuickReservationUtility> utilities;

   
	
   @ManyToOne
   @JoinColumn(name = "adventure_id")
   private Adventure adventure;
   
   
 /*  @ManyToOne
   @JoinColumn(name = "user_id")
   private RegisteredClient reservationClient;*/
   
   

  
}
   
  

