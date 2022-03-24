package com.example.demo.model.users;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.demo.model.cottages.Cottage;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cottageOwner")
@PrimaryKeyJoinColumn(name = "userId")
public class CottageOwner  extends User{
	
	 @OneToMany(mappedBy = "cottageOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private Set<Cottage> cottages;


}
