package model;

import java.util.Set;

public class Instructor extends User {

	private Set<String> biography;
	private Set<Adventure> services;
	
	
	
	public Set<String> getBiography() {
		return biography;
	}



	public void setBiography(Set<String> biography) {
		this.biography = biography;
	}



	public Set<Adventure> getServices() {
		return services;
	}



	public void setServices(Set<Adventure> services) {
		this.services = services;
	}



	public Instructor() {
		super();
	}



	public Instructor(Set<String> biography, Set<Adventure> services) {
		super();
		this.biography = biography;
		this.services = services;
	}
	
	
}
