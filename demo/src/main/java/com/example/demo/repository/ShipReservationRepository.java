package com.example.demo.repository;

import com.example.demo.model.ships.ShipReservation;
import com.example.demo.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipReservationRepository extends JpaRepository<ShipReservation, Integer> {
}
