package com.example.demo.model.ships;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.users.RegisteredUser;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "shipReservation")
public class ShipReservation extends Reservation{
	
	@ManyToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;
	

	
    @ManyToMany
    @JoinTable(
            name = "ship_reservation_utilities",
            joinColumns = @JoinColumn(name = "ship_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "ship_utility_id"))
    private Set<ShipUtility> utilities;

}
