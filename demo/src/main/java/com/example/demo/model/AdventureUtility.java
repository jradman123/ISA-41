package com.example.demo.model;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "adventureUtility")
public class AdventureUtility {
	
	 @Id
	 @Column(name = "id")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	

	 @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	 @JoinColumn(name = "utility_id")
	 private Utility utility;

	 @Column(name = "price", nullable = false)
	 private Double price;

	 @ManyToOne
	 @JoinColumn(name = "adventure_id")
	 private Adventure adventure;
}
