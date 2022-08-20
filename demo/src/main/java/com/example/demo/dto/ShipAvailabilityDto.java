package com.example.demo.dto;

import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.model.ships.ShipAvailability;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipAvailabilityDto {

    public String id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long shipId;

    public ShipAvailabilityDto(ShipAvailability ca) {
        this.id = Long.toString(ca.getId());
        this.startDate=ca.getStartDate();
        this.endDate=ca.getEndDate();
        this.shipId=ca.getShip().getId();
    }
}