package com.example.demo.dto;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.ships.Ship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipDto {

    private String id;
    private String name;
    private String description;
    private String streetNumber;
    private String streetName;
    private String city;
    private String country;
    private String length;
    private String numberOfEngine;
    private String strengthOfEngine;
    private String maxSpeed;
    private String price;
    private String image;
    private String capacity;
    private String type;
    private String cancelationConditions;
    private String fishingEquipment;


    public ShipDto(Ship ship) {
        this.id = Long.toString(ship.getId());
        this.name = ship.getName();
        this.description = ship.getDescription();
        this.streetName= ship.getAddress().getStreetName();
        this.streetNumber=ship.getAddress().getStreetNumber();
        this.city=ship.getAddress().getCity();
        this.country=ship.getAddress().getCountry();
        this.price = Double.toString(ship.getPrice());
        this.length=Double.toString(ship.getLength());
        this.numberOfEngine=ship.getNumberOfEngine();
        this.strengthOfEngine=Double.toString(ship.getStrengthOfEngine());
        this.maxSpeed=Double.toString(ship.getMaxSpeed());
        this.capacity = Integer.toString(ship.getCapacity());
        this.type=ship.getType();
        this.cancelationConditions=Integer.toString(ship.getCancelationConditions());
        this.fishingEquipment=ship.getFishingEquipment();
    }
}