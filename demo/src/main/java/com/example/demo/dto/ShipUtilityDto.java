package com.example.demo.dto;

import com.example.demo.model.Utility;
import com.example.demo.model.cottages.CottageUtility;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.ships.ShipUtility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipUtilityDto {
    public Long id;
    public Long shipId;
    public Long utilityId;
    public String price;
    public String name;



    public ShipUtilityDto(ShipUtility ca) {
        this.id = ca.getId();
        this.shipId=ca.getShip().getId();
        this.utilityId=ca.getUtility().getId();
        this.price=ca.getPrice().toString();
        this.name=ca.getUtility().getName();
    }


}
