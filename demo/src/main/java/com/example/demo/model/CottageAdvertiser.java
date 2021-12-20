package com.example.demo.model;

import java.util.Set;

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
