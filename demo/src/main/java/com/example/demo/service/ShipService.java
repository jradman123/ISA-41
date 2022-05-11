package com.example.demo.service;

import com.example.demo.dto.CottageDto;
import com.example.demo.dto.ShipDto;

import java.util.List;

public interface ShipService {
    List<ShipDto> findAll();
    /*
    public List<ShipDto> findAll();
    public ShipDto findShip(Long id);
    public ShipDto createShip(CreateShipDto newShip);
    public List<ShipDto> getOwnerShips(String email);
    public ResponseEntity<Long> deleteShip(Long id);

     */
}
