package com.example.demo.dto;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.ships.NavigationalEquipment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NavigationDto {

    private String id;
    private String name;
    private String shipId;


    public  NavigationDto(NavigationalEquipment navigation){
        this.id = Long.toString(navigation.getId());
        this.name = navigation.getName().toString();
        this.shipId=Long.toString(navigation.getShip().getId());
         }


}
