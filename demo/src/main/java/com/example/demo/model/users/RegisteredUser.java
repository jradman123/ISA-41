package com.example.demo.model.users;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.demo.model.adventures.AdventureReservation;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.ships.ShipReservation;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "registeredUser")
@PrimaryKeyJoinColumn(name = "userId")
public class RegisteredUser extends User{
	
	 @OneToMany(mappedBy = "registeredUser")
	 private Set<AdventureReservation> adventureReservations;
	
	 @OneToMany(mappedBy = "registeredUser")
	 private Set<ShipReservation> shipReservations;
	
	 @OneToMany(mappedBy = "registeredUser")
	 private Set<CottageReservation> cottageReservations;
	
	  @ManyToMany
	  @JoinTable(
	            name = "user__ship_utilization",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "ship_id"))
	  private Set<Ship> shipUtilization;
	  
	  @ManyToMany
	  @JoinTable(
	            name = "user_instructor_utilization",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
	  private Set<Instructor> instructorUtilization;
	  
	  @ManyToMany
	  @JoinTable(
	            name = "user_cottage_utilization",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "cottage_id"))
	  private Set<Cottage> cottageUtilization;
	  


}
