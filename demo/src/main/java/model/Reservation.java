package model;

import java.time.LocalDate;
import java.util.Set;

public class Reservation {
	private Integer id;
	private LocalDate startDate;
	private Integer duration;
	private Integer numberOfPerson;
	private Set<String> services;
	private double price;
	//slobodni termini za brzu registraciju
	
	
	
	public Reservation(LocalDate startDate, Integer duration, Integer numberOfPerson, Set<String> services,
			double price) {
		super();
		this.startDate = startDate;
		this.duration = duration;
		this.numberOfPerson = numberOfPerson;
		this.services = services;
		this.price = price;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getNumberOfPerson() {
		return numberOfPerson;
	}
	public void setNumberOfPerson(Integer numberOfPerson) {
		this.numberOfPerson = numberOfPerson;
	}
	public Set<String> getServices() {
		return services;
	}
	public void setServices(Set<String> services) {
		this.services = services;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Reservation() {
		super();
	}
	
	
	
	
	
	
	
	
	
}
