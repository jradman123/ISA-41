package com.example.demo.model.users;

import java.util.Set;

import javax.persistence.*;

import com.example.demo.model.ships.Ship;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "shipOwner")
@PrimaryKeyJoinColumn(name = "userId")
@DiscriminatorValue("SHIP_OWNER")
public class ShipOwner  extends User {
	
    @OneToMany(mappedBy = "shipOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ship> ships;

}
