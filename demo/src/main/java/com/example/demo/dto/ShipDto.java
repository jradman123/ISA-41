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

    private Long id;
    private String name;
    private String description;
    private String address;
    private String price;
    private String image;
    private int capacity;
    private String type;


    public ShipDto(Ship ship) {
        this.id = ship.getId();
        this.name = ship.getName();
        this.description = ship.getDescription();
        this.address = ship.getAddress().toString();
        this.price = Double.toString(ship.getPrice());
        this.capacity = ship.getCapacity();
        this.type=ship.getType();
    }
}