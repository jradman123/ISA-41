package com.example.demo.repository;

import com.example.demo.model.ships.ShipAvailability;
import com.example.demo.model.users.ShipOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipAvailabilityRepository  extends JpaRepository<ShipAvailability, Long> {
    @Query(value = "select * from ship_availability where ship_id = ?1", nativeQuery = true)
    List<ShipAvailability> getAllForShip(Long id);
}
