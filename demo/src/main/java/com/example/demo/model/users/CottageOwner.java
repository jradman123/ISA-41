package com.example.demo.model.users;

import java.util.Set;

import javax.persistence.*;

import com.example.demo.model.cottages.Cottage;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cottageOwner")
@PrimaryKeyJoinColumn(name = "userId")
@DiscriminatorValue("COTTAGE_OWNER")
public class CottageOwner  extends User{
	
	 @OneToMany(mappedBy = "cottageOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private Set<Cottage> cottages;


}
