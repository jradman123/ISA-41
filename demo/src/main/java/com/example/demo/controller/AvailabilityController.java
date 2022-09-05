package com.example.demo.controller;


import com.example.demo.dto.CottageAvailabilityDto;
import com.example.demo.dto.CottageDto;
import com.example.demo.dto.ShipAvailabilityDto;
import com.example.demo.dto.ShipUtilityDto;
import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.model.ships.ShipAvailability;
import com.example.demo.service.AvailabilityService;
import com.example.demo.service.impl.AvailabilityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    AvailabilityServiceImpl availabilityService;

    @PreAuthorize("hasAuthority('CottageAdvertiser') || hasAuthority('Admin')")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/getByCottage/{id}")
    public List<CottageAvailabilityDto> getByCottage(@PathVariable Long id) {
        return availabilityService.findByCottage(id);
    }

    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/addCottageAvailability")
    public CottageAvailability addCottageAvailability(@RequestBody CottageAvailabilityDto cottageAvailabilityDto) {
        return availabilityService.add(cottageAvailabilityDto);
    }


    @PreAuthorize("hasAuthority('ShipAdvertiser') || hasAuthority('Admin')")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/getByShip/{id}")
    public List<ShipAvailabilityDto> getbyShip(@PathVariable Long id) {
        return availabilityService.findByShip(id);
    }

    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/addShipAvailability")
    public ShipAvailability addShipAvailability(@RequestBody ShipAvailabilityDto shipAvailabilityDto) {
        return availabilityService.addShipAvailability(shipAvailabilityDto);
    }
}
