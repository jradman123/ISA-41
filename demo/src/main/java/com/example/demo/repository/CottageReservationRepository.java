package com.example.demo.repository;

import com.example.demo.model.Image;
import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.model.cottages.CottageReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CottageReservationRepository extends JpaRepository<CottageReservation, Long> {
    @Query(value = "select * from cottage_reservation where cottage_id = ?1", nativeQuery = true)
    List<CottageReservation> getAllForCottage(Long cottageId);
}
