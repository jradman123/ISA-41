package com.example.demo.service;

import com.example.demo.dto.CottageUtilityDto;
import com.example.demo.dto.ShipUtilityDto;
import com.example.demo.dto.UtilityDto;
import com.example.demo.model.Utility;
import com.example.demo.model.cottages.CottageUtility;
import com.example.demo.model.ships.ShipUtility;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UtiilityService {


    List<CottageUtilityDto> getUtilityByCottage(Long id);

    List<ShipUtilityDto> getUtilitiesbyBoat(Long id);

    ResponseEntity<Long> deleteUtiliry(Long id, Long idCottage);

    CottageUtility add(CottageUtilityDto cottageUtilityDto);

    CottageUtility updateCottageUtility(CottageUtilityDto cottageUtilityDto,Long id);

    ShipUtility addUtilityByShip(ShipUtilityDto shipUtilityDto);

    ResponseEntity<Long> deleteUtilirybyShip(Long id, Long idShip);
}
