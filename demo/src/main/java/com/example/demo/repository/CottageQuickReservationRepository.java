package com.example.demo.repository;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageQuickReservation;
import com.example.demo.model.cottages.CottageReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CottageQuickReservationRepository  extends JpaRepository<CottageQuickReservation, Long> {
    @Query(value = "select * from cottage_quick_reservation where cottage_id = ?1", nativeQuery = true)
    List<CottageQuickReservation> getAllForCottage(Long cottageId);

}
