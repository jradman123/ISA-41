package com.example.demo.repository;

import com.example.demo.model.ships.Ship;
import com.example.demo.model.users.ShipOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipOwnerRepository  extends JpaRepository<ShipOwner, Long> {
}
