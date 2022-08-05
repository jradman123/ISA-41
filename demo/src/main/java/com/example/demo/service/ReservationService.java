package com.example.demo.service;

import com.example.demo.dto.CottageReservationViewDto;
import com.example.demo.dto.CreateReservationDto;
import com.example.demo.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    List<CottageReservationViewDto> getReservationsByCottage(Long id);

    List<ReservationDto> getReservationsByBoat(Long id);

    ReservationDto getById(Long id);

    void createReservation(CreateReservationDto createReservationDto);
}
