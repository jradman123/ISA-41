package com.example.demo.repository;

import com.example.demo.model.ships.Ship;
import com.example.demo.model.users.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository  extends JpaRepository<Ship, Long> {
}
