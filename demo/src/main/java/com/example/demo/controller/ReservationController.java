package com.example.demo.controller;


import com.example.demo.dto.CreateReservationDto;
import com.example.demo.dto.ReservationDto;
import com.example.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")

public class ReservationController {


    @Autowired
    ReservationService reservationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/findReservationsByCottage/{id}")
    public List<ReservationDto> getReservationsByCottage(@PathVariable Long id) {
        List<ReservationDto> reservationDtos = this.reservationService.getReservationsByCottage(id);
        return reservationDtos;
    }


    @CrossOrigin(origins = "http://localhost:4200")
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


    @PostMapping(value="/createReservation")
    public ResponseEntity<HttpStatus> createReservation(@RequestBody CreateReservationDto createReservationDto) {
        reservationService.createReservation(createReservationDto);
    return ResponseEntity.noContent().build();
    }
}
