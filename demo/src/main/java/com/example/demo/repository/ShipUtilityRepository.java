package com.example.demo.repository;


import com.example.demo.model.ships.ShipUtility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipUtilityRepository extends JpaRepository<ShipUtility, Long> {
}
