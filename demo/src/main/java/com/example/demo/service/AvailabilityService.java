package com.example.demo.service;

import com.example.demo.dto.CottageAvailabilityDto;
import com.example.demo.dto.ShipAvailabilityDto;
import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.model.ships.ShipAvailability;

import java.util.List;

public interface AvailabilityService {
    List<CottageAvailabilityDto> findByCottage(Long id);

    CottageAvailability add(CottageAvailabilityDto cottageAvailabilityDto);

    List<ShipAvailabilityDto> findByShip(Long id);

    ShipAvailability addShipAvailability(ShipAvailabilityDto shipAvailabilityDto);
}
