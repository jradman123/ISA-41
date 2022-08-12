package com.example.demo.model.cottages;




import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.model.Utility;
import com.example.demo.model.adventures.Adventure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "cottageUtility")
public class CottageUtility {
	
	 @Id
	 @Column(name = "id")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 @ManyToOne
	 @JoinColumn(name = "cottage_id")
	 private Cottage cottage;
	 

	 @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	 @JoinColumn(name = "utility_id")
	 private Utility utility;
	 
	 @Column(name = "price")
	 private Double price;

	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;


	public CottageUtility(String price, Cottage cottage, Utility utility) {
		this.cottage=cottage;
		this.utility=utility;
		this.price=Double.parseDouble(price);
		this.isDeleted=false;
	}
}
