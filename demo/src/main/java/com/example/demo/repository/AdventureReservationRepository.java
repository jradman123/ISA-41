package com.example.demo.repository;

import com.example.demo.model.adventures.AdventureReservation;
import com.example.demo.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AdventureReservationRepository extends JpaRepository<AdventureReservation,Long> {

    @Query(value = "select * from adventure_reservation a where instructor_id = ?1 and reservation_end >= ?2", nativeQuery = true)
    List<AdventureReservation> getAllCurrentAndFutureInstructorsReservations(int instructorId, LocalDateTime now);

    @Query(value = "select * from adventure_reservation a where user_id = ?1 and reservation_end >= ?2", nativeQuery = true)
    List<AdventureReservation> getAllReservationsForClient(int clientId, LocalDateTime now);

    @Query(value = "select * from adventure_reservation a where instructor_id = ?1", nativeQuery = true)
    List<AdventureReservation> getAllInstructorsReservations(int instructorId);

    @Query(value = "select * from adventure_reservation a where adventure_id = ?3 and  reservation_start <= ?2 and  ?1 <= reservation_start and reservation_end <= ?2 and ?1 < reservation_end and is_canceled = false", nativeQuery = true)
    List<AdventureReservation> getAllForAdventureInDateRange(LocalDateTime start, LocalDateTime end,int id);

    @Query(value = "select * from adventure_reservation a where adventure_id = ?3 and  reservation_start <= ?2 and  ?1 <= reservation_start and reservation_end <= ?2 and ?1 < reservation_end and is_canceled = true", nativeQuery = true)
    List<AdventureReservation> getAllCancelledForAdventureInDateRange(LocalDateTime start, LocalDateTime end,int id);

    @Query(value = "select * from adventure_reservation a where adventure_id = ?1 ", nativeQuery = true)
    List<AdventureReservation> getAllForAdventure(int id);
}
