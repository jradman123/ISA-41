package com.example.demo.repository;

import com.example.demo.model.Image;
import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.model.cottages.CottageReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CottageReservationRepository extends JpaRepository<CottageReservation, Long> {
    @Query(value = "select * from reservation where cottage_id = ?1", nativeQuery = true)
    List<CottageReservation> getAllForCottage(Long cottageId);


    @Query(value = "select * from reservation a where cottage_id = ?3 and  reservation_start <= ?2 and  ?1 <= reservation_start and reservation_end <= ?2 and ?1 < reservation_end and is_canceled = false", nativeQuery = true)
    List<CottageReservation> getAllForCottageInDateRange(LocalDateTime start, LocalDateTime end,Long id);

    @Query(value = "select * from reservation a where cottage_id = ?3 and  reservation_start <= ?2 and  ?1 <= reservation_start and reservation_end <= ?2 and ?1 < reservation_end and is_canceled = true", nativeQuery = true)
    List<CottageReservation> getAllCancelledForCottageInDateRange(LocalDateTime start, LocalDateTime end,Long id);

    @Query(value = "select * from reservation a where cottage_id = ?3 and  reservation_end <= ?2 and  ?1 <= reservation_start", nativeQuery = true)
    List<CottageReservation> getByDate(LocalDateTime startDate, LocalDateTime endDate, Long id);
}
