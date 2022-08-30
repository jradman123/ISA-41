package com.example.demo.repository;

import com.example.demo.model.cottages.Room;
import com.example.demo.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository  extends JpaRepository<Reservation, Long> {

}
