package com.example.demo.repository;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.users.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface ShipRepository  extends JpaRepository<Ship, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Ship c WHERE c.id = :id")
    @QueryHints({@QueryHint(name ="javax.persistance.lock.timeout",value="0")})
    Ship findShipById(Long id);
}
