package com.example.demo.model.ships;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@Table(name = "navigationalEquipment")

	public class NavigationalEquipment {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;
		
		@Column(name = "name", nullable = false)
	    private EquipmentType name;

	}

