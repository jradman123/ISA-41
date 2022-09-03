package com.example.demo.repository;

import com.example.demo.model.Report;
import com.example.demo.model.ships.NavigationalEquipment;
import com.example.demo.model.users.InstructorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query(value = "select * from report where approvedby_admin=false and unapprovedby_admin=false", nativeQuery = true)
    List<Report> findAllUnseen();
}
