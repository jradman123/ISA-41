package com.example.demo.repository;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.users.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipRepository  extends JpaRepository<Ship, Long> {
    @Query(value = "select * from ships where deleted = false", nativeQuery = true)
    List<Ship> getAllUndeleted();
}
