package com.example.demo.repository;

import com.example.demo.model.adventures.AdventureReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdventureReservationRepository extends JpaRepository<AdventureReservation,Long> {

}
