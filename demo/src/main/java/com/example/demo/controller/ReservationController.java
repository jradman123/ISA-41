package com.example.demo.controller;


import com.example.demo.dto.ReservationDto;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
