package com.example.demo.repository;

import com.example.demo.model.adventures.AdventureReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AdventureReservationRepository extends JpaRepository<AdventureReservation,Long> {

    @Query(value = "select * from reservation a where instructor_id = ?1 and reservation_end >= ?2", nativeQuery = true)
    List<AdventureReservation> getAllInstructorsReservations(int instructorId, LocalDateTime now);
}
