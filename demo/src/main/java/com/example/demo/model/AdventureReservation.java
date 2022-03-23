package com.example.demo.model;

import java.time.LocalDate;
import java.util.Set;

public class AdventureReservation extends Reservation {
	
	private Address address; //mjesto?
	//slobodni termini
	
	
	public AdventureReservation(LocalDate startDate, Integer duration, Integer numberOfPerson, Set<String> services,
			double price) {
		super(startDate, duration, numberOfPerson, services, price);
	}



	public AdventureReservation(LocalDate startDate, Integer duration, Integer numberOfPerson, Set<String> services,
			double price, Address address) {
		super(startDate, duration, numberOfPerson, services, price);
		this.address = address;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	

}
