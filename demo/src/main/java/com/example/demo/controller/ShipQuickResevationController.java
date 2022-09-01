package com.example.demo.controller;

import com.example.demo.dto.CottageQuickReservationDto;
import com.example.demo.dto.ShipQuickReservationDto;
import com.example.demo.dto.ShipQuickReservationResponse;
import com.example.demo.model.ships.ShipQuickReservation;
import com.example.demo.service.ShipQuickReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ship-quick-reservation")
public class ShipQuickResevationController {
    @Autowired
    private ShipQuickReservationService shipQuickReservationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @GetMapping(value="/getAppByShip/{id}")
    public List<ShipQuickReservationResponse> getAllShip(@PathVariable Long id) {
        return this.shipQuickReservationService.findApp(id);
    }

    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @DeleteMapping(value="/deleteApp/{id}")
    public ResponseEntity<Long> deleteApp(@PathVariable Long id) {
        return this.shipQuickReservationService.deleteApp(id);
    }




    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @PostMapping(value="/createApp")
    public ShipQuickReservation createApp(@RequestBody ShipQuickReservationDto dto) {
        return this.shipQuickReservationService.createAppointment(dto);
    }

}
