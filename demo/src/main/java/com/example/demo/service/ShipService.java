package com.example.demo.service;


import com.example.demo.dto.AverageMarkDto;
import com.example.demo.dto.CottageDto;
import com.example.demo.dto.ShipDto;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.ships.Ship;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShipService {
    List<ShipDto> findAll();

    ShipDto findShip(Long id);

    List<ShipDto> getOwnerShips(String email);

    public ResponseEntity<Long> deleteShip(Long id);


    Ship createShip(ShipDto newShip);


    ShipDto editShip(ShipDto newShip);

    Ship findShipById(Long objectId);

    Ship create(Ship ship);

    AverageMarkDto getShipReport(Long id);
}
