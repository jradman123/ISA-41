package com.example.demo.service;

import com.example.demo.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    List<ReservationDto> getReservationsByCottage(Long id);

    List<ReservationDto> getReservationsByBoat(Long id);
}
