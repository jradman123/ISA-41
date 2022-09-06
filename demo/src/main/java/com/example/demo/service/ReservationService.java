package com.example.demo.service;

import com.example.demo.dto.DetailsAboutReservation;
import com.example.demo.dto.ReservationViewDto;
import com.example.demo.dto.CreateReservationDto;
import com.example.demo.dto.ReservationDto;

import com.example.demo.model.adventures.AdventureReservation;

import com.example.demo.model.cottages.CottageReservation;

import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.ships.ShipReservation;
import com.example.demo.model.users.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<ReservationViewDto> getReservationsByCottage(Long id);
    List<ReservationDto> getReservationsByBoat(Long id);
    ReservationDto getById(Long id);
    void createReservation(CreateReservationDto createReservationDto);
    Reservation createCottageReservation(CreateReservationDto createReservationDto);
    Reservation createShipReservation(CreateReservationDto createReservationDto);
    void notifyUserForReservation(CreateReservationDto dto);
    List<ReservationViewDto> getPastReservationsByCottage(Long id);
    List<ReservationViewDto> getReservationsByShip(Long id);
    List<ReservationViewDto> getPastReservationsByShip(Long id);
    List<ReservationViewDto> getPastReservationsForAdventure(int id);
    List<ReservationViewDto> getReservationsForAdventure(int id);
    List<ReservationViewDto> getFutureReservationsForAdventure(int id);
    boolean  reservationsExistForAdventure(int id);
    String createAdventureReservation(CreateReservationDto createReservationDto);
    boolean hasInstructorReservationsForRange(int id, LocalDateTime startTime, LocalDateTime endTime);
    List<ReservationViewDto> getAllCurrentAndFutureInstructorsReservations(int instructorId);
    List<ReservationViewDto> getAllInstructorsReservations(int instructorId);
    DetailsAboutReservation getDetails(Long id);
    String getOwnersEmail(Reservation reservation);
    String getOwnersName(Reservation reservation);
    Double calculatePriceForAdventure(CreateReservationDto createReservationDto);
    User updatePoints(User user);
    List<Reservation> getByUser(int id);
    List<AdventureReservation> getByInstructor(int id);
    List<CottageReservation> getByCottageOwner(int id);
    List<ShipReservation> getByShipOwner(int id);
}
