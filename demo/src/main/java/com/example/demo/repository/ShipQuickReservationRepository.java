package com.example.demo.repository;

import com.example.demo.model.ships.ShipQuickReservation;
import com.example.demo.model.users.ShipOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipQuickReservationRepository extends JpaRepository<ShipQuickReservation, Long> {
}
