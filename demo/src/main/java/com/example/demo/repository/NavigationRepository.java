package com.example.demo.repository;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.ships.NavigationalEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NavigationRepository extends JpaRepository<NavigationalEquipment, Long> {
}
