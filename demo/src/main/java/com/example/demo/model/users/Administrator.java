package com.example.demo.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.demo.model.Address;
import com.example.demo.model.enumeration.AdminType;

import com.example.demo.model.enumeration.UserType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "administrator")
@PrimaryKeyJoinColumn(name = "userId")
@DiscriminatorValue("ADMIN")
public class Administrator extends User {
	private AdminType type;
	private boolean firstLogin;

	public Administrator() {
	}

	public Administrator(Integer id, String firstName, String lastName, String password, String email, String phoneNumber, Address address, String jmbg, UserType userType, String descriptionOfRegistration, Boolean isActivated, boolean deleted, AdminType type) {
		super(id, firstName, lastName, password, email, phoneNumber, address, jmbg, userType, descriptionOfRegistration, isActivated, deleted);
		this.type = type;
		this.firstLogin = true;
	}

	public AdminType getType() {
		return type;
	}

	public void setType(AdminType type) {
		this.type = type;
	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
}
