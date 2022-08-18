package com.example.demo.service;


import com.example.demo.dto.NavigationDto;
import com.example.demo.dto.UtilityDto;
import com.example.demo.model.ships.NavigationalEquipment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NavigationService {
    List<NavigationDto> findNavigationbyBoat(Long id);

    NavigationalEquipment createNavigation(NavigationDto newNavigation);

    ResponseEntity<Long> deleteNavigationByShip(Long id, Long idShip);
}
