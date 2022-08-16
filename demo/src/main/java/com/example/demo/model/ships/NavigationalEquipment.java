package com.example.demo.model.ships;

import javax.persistence.*;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.enumeration.EquipmentType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



	@Entity
	@Getter
	@Setter
	@RequiredArgsConstructor
	@AllArgsConstructor
	@Table(name = "navigationEquipment")

	public class NavigationalEquipment {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;
		
		@Column(name = "name", nullable = false)
	    private String name;


		@Column(name = "isDeleted", nullable = false)
		private boolean isDeleted;

		@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
		@JoinColumn(name = "ship_id",nullable = true)
		private Ship ship;


	}

