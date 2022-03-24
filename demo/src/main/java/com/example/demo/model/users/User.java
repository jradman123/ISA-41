package com.example.demo.model.users;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.model.Address;
import com.example.demo.model.enumeration.UserType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="users")

public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="firstName", nullable = false)
	private String firstName;
	
	@Column(name="lastName", nullable = false)
	private String lastName;
	
	@Column(name="userName",nullable = false)
	private String userName;
	
	@Column(name="password", nullable = false)
	private String password;	
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="phoneNumber", nullable = false)
	private String phoneNumber;
	
	 @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	 @JoinColumn(name = "address", referencedColumnName = "id")
	 private Address address;
	
	@Column(name="jmbg",unique=true)
	private String jmbg;
	
	@Column(name="userType", nullable = false)
	private UserType userType;
	
	@Column(name="descriptionOfRegistration")
	private String descriptionOfRegistration;
	
	@Column(name="is_activated")
	private Boolean isActivated=false;
	
	 @Column(name = "deleted")
	 private boolean deleted = false;
	 
	 public String getFullName() {
	        return this.firstName + " " + this.lastName;
	    }
	 
	public User() {
	super();
	}

	
	public User(Integer id, String firstName, String lastName, String userName, String password, 
			String email, String phoneNumber, Address address, String jmbg, UserType userType,
			String descriptionOfRegistration, Boolean isActivated, boolean deleted) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.jmbg = jmbg;
		this.userType = userType;
		this.descriptionOfRegistration = descriptionOfRegistration;
		this.isActivated = isActivated;
		this.deleted = deleted;
	}


	

	

	
	
	
	
	
	

}
