package com.example.demo.model.users;

import java.time.LocalDate;

import javax.persistence.*;

import com.example.demo.model.Address;
import com.example.demo.model.enumeration.UserType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="d_type", discriminatorType = DiscriminatorType.STRING)
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="firstName", nullable = false)
	private String firstName;
	
	@Column(name="lastName", nullable = false)
	private String lastName;

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
	
	@Column(name="userType")
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

	
	public User(Integer id, String firstName, String lastName,String password,
			String email, String phoneNumber, Address address, String jmbg, UserType userType,
			String descriptionOfRegistration, Boolean isActivated, boolean deleted) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public Boolean getActivated() {
		return isActivated;
	}

	public void setActivated(Boolean activated) {
		isActivated = activated;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}


}
