package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fishingEquipment")
public class FishingEquipment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	 @Column(name = "name", nullable = false)
	    private String fishingEquipmentName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFishingEquipmentName() {
		return fishingEquipmentName;
	}

	public void setFishingEquipmentName(String fishingEquipmentName) {
		this.fishingEquipmentName = fishingEquipmentName;
	}

	public FishingEquipment(Long id, String fishingEquipmentName) {
		super();
		this.id = id;
		this.fishingEquipmentName = fishingEquipmentName;
	}
	 
	 
}
