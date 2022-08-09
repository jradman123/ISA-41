package com.example.demo.repository;

import com.example.demo.model.adventures.FishingEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishingEquipmentRepository extends JpaRepository<FishingEquipment, Long> {
}
