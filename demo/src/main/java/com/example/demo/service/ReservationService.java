package com.example.demo.service;

import com.example.demo.dto.ReservationViewDto;
import com.example.demo.dto.CreateReservationDto;
import com.example.demo.dto.ReservationDto;
import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.reservation.Reservation;

import java.util.List;

public interface ReservationService {
    List<ReservationViewDto> getReservationsByCottage(Long id);

    List<ReservationDto> getReservationsByBoat(Long id);

    ReservationDto getById(Long id);

    void createReservation(CreateReservationDto createReservationDto);

    Reservation createCottageReservation(CreateReservationDto createReservationDto);

    void notifyUserForReservation(CreateReservationDto dto);

    List<ReservationViewDto> getPastReservationsByCottage(Long id);

    List<ReservationViewDto> getReservationsByShip(Long id);

    List<ReservationViewDto> getPastReservationsByShip(Long id);
}
