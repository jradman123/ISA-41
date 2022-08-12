package com.example.demo.repository;

import com.example.demo.model.Report;
import com.example.demo.model.ships.NavigationalEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
