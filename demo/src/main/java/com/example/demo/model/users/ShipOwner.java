package com.example.demo.model.users;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.demo.model.ships.Ship;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "shipOwner")
@PrimaryKeyJoinColumn(name = "userId")

public class ShipOwner  extends User {
	
    @OneToMany(mappedBy = "shipOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ship> ships;

}
