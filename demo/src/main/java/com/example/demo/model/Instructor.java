package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.demo.model.adventures.Adventure;

@Entity
@Table(name = "instructor")
@PrimaryKeyJoinColumn(name = "id")
public class Instructor extends User {

	@Column(name = "biography")
    private String biography = "This user provided no biography.";
	
	 @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
	  private Set<Adventure> adventures;

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Set<Adventure> getAdventures() {
		return adventures;
	}

	public void setAdventures(Set<Adventure> adventures) {
		this.adventures = adventures;
	}
	
	 
	
	
	

}
