package com.example.demo.repository;

import com.example.demo.model.cottages.CottageQuickReservation;
import com.example.demo.model.ships.ShipQuickReservation;
import com.example.demo.model.users.ShipOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipQuickReservationRepository extends JpaRepository<ShipQuickReservation, Long> {
    @Query(value = "select * from ship_quick_reservation where ship_id = ?1", nativeQuery = true)
    List<ShipQuickReservation> getAllForShip(Long shipId);

}
