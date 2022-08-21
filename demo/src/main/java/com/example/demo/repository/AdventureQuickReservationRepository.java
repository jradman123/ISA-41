package com.example.demo.repository;

import com.example.demo.model.adventures.AdventureQuickReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureQuickReservationRepository extends JpaRepository<AdventureQuickReservation,Long> {
}
