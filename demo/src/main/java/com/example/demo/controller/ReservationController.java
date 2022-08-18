package com.example.demo.controller;


import com.example.demo.dto.ReservationViewDto;
import com.example.demo.dto.CreateReservationDto;
import com.example.demo.dto.ReservationDto;
import com.example.demo.dto.ShipReservationViewDto;
import com.example.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")

public class ReservationController {


    @Autowired
    ReservationService reservationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @GetMapping(value = "/findReservationsByCottage/{id}")
    public List<ReservationViewDto> getReservationsByCottage(@PathVariable Long id) {
        List<ReservationViewDto> reservationDtos = this.reservationService.getReservationsByCottage(id);
        return reservationDtos;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @GetMapping(value = "/findPastReservationsByCottage/{id}")
    public List<ReservationViewDto> getPastReservationsByCottage(@PathVariable Long id) {
        List<ReservationViewDto> reservationDtos = this.reservationService.getPastReservationsByCottage(id);
        for(ReservationViewDto reservationViewDto:reservationDtos)
            System.out.print(reservationViewDto.getResEnd());
        return reservationDtos;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @GetMapping(value = "/findReservationsByShip/{id}")
    public List<ReservationViewDto> getReservationsByShip(@PathVariable Long id) {
        List<ReservationViewDto> reservationDtos = this.reservationService.getReservationsByShip(id);
        return reservationDtos;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @GetMapping(value = "/findPastReservationsByShip/{id}")
    public List<ReservationViewDto> getPastReservationsByShip(@PathVariable Long id) {
        List<ReservationViewDto> reservationDtos = this.reservationService.getPastReservationsByShip(id);
        for(ReservationViewDto reservationViewDto:reservationDtos)
            System.out.print(reservationViewDto.getResEnd());
        return reservationDtos;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @GetMapping(value = "/findReservationsByBoat/{id}")
    public List<ReservationDto> getReservationsByBoat(@PathVariable Long id) {
        List<ReservationDto> reservationDtos = this.reservationService.getReservationsByBoat(id);
        return reservationDtos;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "getById/{id}")
    public ReservationDto getById(@PathVariable Long id) {
        return this.reservationService.getById(id);
    }

    @PreAuthorize("hasAuthority('CottageAdvertiser') || hasAuthority('ShipAdvertiser')")
    @PostMapping(value="/createReservation")
    public ResponseEntity<HttpStatus> createReservation(@RequestBody CreateReservationDto createReservationDto) {
        reservationService.createReservation(createReservationDto);
    return ResponseEntity.noContent().build();
    }
}
