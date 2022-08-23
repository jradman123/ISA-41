package com.example.demo.repository;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageQuickReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CottageQuickReservationRepository  extends JpaRepository<CottageQuickReservation, Long> {
}
