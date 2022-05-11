package com.example.demo.service;


import com.example.demo.dto.ShipDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShipService {
    List<ShipDto> findAll();

    ShipDto findShip(Long id);

    public ResponseEntity<Long> deleteShip(Long id);


}
