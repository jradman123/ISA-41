package com.example.demo.model.users;

import java.util.Set;

import javax.persistence.*;

import com.example.demo.model.adventures.Adventure;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "instructor")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("INSTRUCTOR")
public class Instructor extends User {

	@Column(name = "biography")
    private String biography = "This user provided no biography.";
	
	 @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
	  private Set<Adventure> adventures;


	
	 
	
	
	

}
