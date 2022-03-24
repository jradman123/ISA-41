package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.demo.model.enumeration.AdminType;
import com.example.demo.model.users.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "administrator")
@PrimaryKeyJoinColumn(name = "userId")
public class Administrator extends User {
	private AdminType type;
}
