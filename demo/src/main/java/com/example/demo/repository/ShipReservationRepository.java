package com.example.demo.repository;

import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.ships.ShipReservation;
import com.example.demo.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ShipReservationRepository extends JpaRepository<ShipReservation, Integer> {
    @Query(value = "select * from reservation where ship_id = ?1", nativeQuery = true)
    List<ShipReservation> getAllForShip(Long shipId);

    @Query(value = "select * from reservation a where ship_id = ?3 and  reservation_start <= ?2 and  ?1 <= reservation_start  and ?1 < reservation_end", nativeQuery = true)
    List<ShipReservation> getAllForShipInDateRange(LocalDateTime minusYears, LocalDateTime now, Long id);

    @Query(value = "select * from reservation a where ship_id = ?3 and  reservation_start <= ?2 and  ?1 <= reservation_start and reservation_end <= ?2 and ?1 < reservation_end and is_canceled = true", nativeQuery = true)
    List<ShipReservation> getAllCancelledForShipInDateRange(LocalDateTime start, LocalDateTime end,Long id);

}
