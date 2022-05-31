package com.example.demo.controller;


import com.example.demo.dto.CottageAvailabilityDto;
import com.example.demo.dto.CottageDto;
import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.service.AvailabilityService;
import com.example.demo.service.impl.AvailabilityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    AvailabilityServiceImpl availabilityService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/getByCottage/{id}")
    public List<CottageAvailabilityDto> getByCottage(@PathVariable Long id) {
        return availabilityService.findByCottage(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/addCottageAvailability")
    public CottageAvailability addCottageAvailability(@RequestBody CottageAvailabilityDto cottageAvailabilityDto) {
        return availabilityService.add(cottageAvailabilityDto);
    }
}
