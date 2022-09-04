package com.example.demo.repository;

import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.ships.ShipReservation;
import com.example.demo.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipReservationRepository extends JpaRepository<ShipReservation, Integer> {
    @Query(value = "select * from ship_reservation where ship_id = ?1", nativeQuery = true)
    List<ShipReservation> getAllForShip(Long shipId);
}
