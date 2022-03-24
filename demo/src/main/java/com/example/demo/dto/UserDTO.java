package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.model.Address;
import com.example.demo.model.User;


public class UserDTO {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String password;
	private LocalDate dateOfBirth;
	private String email;
	private String phoneNumber;
	private Address address;
	private String jmbg;
	private String descriptionOfRegistration;
	private Boolean isActivated;
	
	
	
	public UserDTO() {}

 
	
	
	public UserDTO(Integer id, String firstName, String lastName, String password, String email,
			String phoneNumber, String jmbg, String descriptionOfRegistration, Boolean isActivated) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.jmbg = jmbg;
		this.descriptionOfRegistration = descriptionOfRegistration;
		this.isActivated = isActivated;
	}





	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	


	



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public String getJmbg() {
		return jmbg;
	}



	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}



	public String getDescriptionOfRegistration() {
		return descriptionOfRegistration;
	}



	public void setDescriptionOfRegistration(String descriptionOfRegistration) {
		this.descriptionOfRegistration = descriptionOfRegistration;
	}



	public Boolean getIsActivated() {
		return isActivated;
	}



	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}
	
	

}
