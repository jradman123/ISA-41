package com.example.demo.repository;

import com.example.demo.model.Image;
import com.example.demo.model.cottages.CottageReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CottageReservationRepository extends JpaRepository<CottageReservation, Long> {
}
