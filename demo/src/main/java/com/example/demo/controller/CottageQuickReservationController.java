package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureQuickReservation;
import com.example.demo.model.adventures.AdventureUtility;
import com.example.demo.model.cottages.CottageQuickReservation;
import com.example.demo.model.reservation.Appointment;
import com.example.demo.service.CottageQuickReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cottage-quick-reservation")
public class CottageQuickReservationController {

    @Autowired
    private CottageQuickReservationService cottageQuickReservationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @GetMapping(value="/getAppByCottage/{id}")
    public List<CottageQuickReservationResponse> getAllByCottage(@PathVariable Long id) {
        return this.cottageQuickReservationService.findApp(id);
    }
    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @DeleteMapping(value="/deleteApp/{id}")
    public ResponseEntity<Long> deleteApp(@PathVariable Long id) {
        return this.cottageQuickReservationService.deleteApp(id);
    }




    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @PostMapping(value="/createApp")
    public CottageQuickReservation createApp(@RequestBody CottageQuickReservationDto dto) {
        return this.cottageQuickReservationService.createAppointment(dto);
    }

}
