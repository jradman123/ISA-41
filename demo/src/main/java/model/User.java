package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.enumeration.UserType;

@Entity
@Table(name="users")
public class User {
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="first_name", nullable = false)
	private String firstName;
	
	@Column(name="last_name", nullable = false)
	private String lastName;
	
	@Column(name="user_name",nullable = false)
	private String userName;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="date_of_birth", nullable = false)
	private LocalDate dateOfBirth;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name="address", nullable = false)
	private Address address;
	
	@Column(name="jmbg",unique=true)
	private String jmbg;
	
	@Column(name="user_type", nullable = false)
	private UserType type;
	@Column(name="description_of_registration")
	
	private String descriptionOfRegistration;
	
	@Column(name="is_activated", nullable = false)
	private Boolean isActivated;
	
	public User() {
		super();
	}

	public User(Integer id, String firstName, String lastName, String userName, String password, LocalDate dateOfBirth,
			String email, String phoneNumber, Address address, String jmbg, UserType type,
			String descriptionOfRegistration, Boolean isActivated) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.jmbg = jmbg;
		this.type = type;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
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
