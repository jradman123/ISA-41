package com.example.demo.model.ships;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.model.Utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "shipUtility")
public class ShipUtility {
	
	 @Id
	 @Column(name = "id")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 @ManyToOne
     @JoinColumn(name = "utility_id")
	 private Utility utility;
	    
	 @ManyToOne
	 @JoinColumn(name = "ship_id")
	 private Ship ship;


	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;



	 @Column(name = "price", nullable = false)
	 private Double price;


    public ShipUtility(String price, Ship ship, Utility utility) {
		this.ship=ship;
		this.utility=utility;
		this.price=Double.parseDouble(price);
		this.isDeleted=false;
    }
}
