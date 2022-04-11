package com.example.demo.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.demo.model.enumeration.AdminType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "administrator")
@PrimaryKeyJoinColumn(name = "userId")
@DiscriminatorValue("ADMIN")
public class Administrator extends User {
	private AdminType type;
}
