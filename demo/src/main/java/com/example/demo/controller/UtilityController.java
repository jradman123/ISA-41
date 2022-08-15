package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.cottages.CottageUtility;
import com.example.demo.model.ships.ShipUtility;
import com.example.demo.service.impl.UtilityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/utilities")
public class UtilityController {
    @Autowired
    private UtilityServiceImpl utilityService;


    @CrossOrigin(origins = "http://localhost:4200")
    //radi samo vlasnik vikendice
    @DeleteMapping(value = "/deleteUtilityByCottage/{id}/{idCottage}")
    public ResponseEntity<Long> deleteUtility(@PathVariable Long id,@PathVariable Long idCottage) {
        return this.utilityService.deleteUtiliry(id,idCottage);
    }



    @CrossOrigin(origins = "http://localhost:4200")
    //radi samo vlasnik vikendice
    @DeleteMapping(value = "/deleteUtilityByShip/{id}/{idShip}")
    public ResponseEntity<Long> deleteUtilitybyShip(@PathVariable Long id,@PathVariable Long idShip) {
        return this.utilityService.deleteUtilirybyShip(id,idShip);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/findUtilitiesByCottage/{id}")
    public List<CottageUtilityDto> getUtilitesByCottage(@PathVariable Long id) {


        return utilityService.getUtilityByCottage(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/findUtilitiesByBoat/{id}")
    public List<ShipUtilityDto> getUtilitiesbyBoat(@PathVariable Long id) {


        return utilityService.getUtilitiesbyBoat(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/addCottageUtility")
    public CottageUtility addCottageUtility(@RequestBody CottageUtilityDto cottageUtilityDto) {
        return utilityService.add(cottageUtilityDto);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/addShipUtility")
    public ShipUtility addShipUtility(@RequestBody ShipUtilityDto shipUtilityDto) {
        return utilityService.addUtilityByShip(shipUtilityDto);
    }






}
