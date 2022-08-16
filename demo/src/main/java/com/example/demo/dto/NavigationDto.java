package com.example.demo.dto;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.ships.NavigationalEquipment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
