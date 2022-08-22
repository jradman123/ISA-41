package com.example.demo.repository;

import com.example.demo.model.adventures.AdventureQuickReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdventureQuickReservationRepository extends JpaRepository<AdventureQuickReservation,Long> {

    @Query(value = "select * from adventure_quick_reservation a where adventure_id = ?1", nativeQuery = true)
    List<AdventureQuickReservation> findAllByAdventureId(int adventure_id);
}
