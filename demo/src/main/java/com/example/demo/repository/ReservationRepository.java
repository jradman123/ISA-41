package com.example.demo.repository;

import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.cottages.Room;
import com.example.demo.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository  extends JpaRepository<Reservation, Long> {

    @Query(value = "select * from reservation a where user_id = ?1 and is_canceled = false and reservation_end >= ?2", nativeQuery = true)
    List<Reservation> getCurrentAndFutureForClient(int clientId, LocalDateTime now);

    @Query(value = "select * from reservation a where  ?1 <= reservation_start <= ?2 and ?1 < reservation_end <= ?2", nativeQuery = true)
    List<Reservation> getAllInLastWeek(LocalDateTime start, LocalDateTime end);
}
