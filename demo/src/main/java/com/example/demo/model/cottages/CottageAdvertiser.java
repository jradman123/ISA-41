package com.example.demo.model.cottages;

import java.util.Set;

import com.example.demo.model.users.User;

public class CottageAdvertiser extends User {
	private Set<Cottage> cottages;

	
	
	public CottageAdvertiser() {
		super();
	}

	public CottageAdvertiser(Set<Cottage> cottages) {
		super();
		this.cottages = cottages;
	}

	public Set<Cottage> getCottages() {
		return cottages;
	}

	public void setCottages(Set<Cottage> cottages) {
		this.cottages = cottages;
	}
	
	

}
