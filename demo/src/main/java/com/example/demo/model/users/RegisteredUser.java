package com.example.demo.model.users;

import java.util.Set;

import javax.persistence.*;

import com.example.demo.model.adventures.AdventureReservation;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.ships.ShipReservation;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "registeredUser")
@PrimaryKeyJoinColumn(name = "userId")
@DiscriminatorValue("REGISTERED_USER")
public class RegisteredUser extends User{

		@Column
		int penalties;

	 @JsonManagedReference
	 @OneToMany(mappedBy = "registeredUser",fetch = FetchType.EAGER)
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
