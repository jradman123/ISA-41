package com.example.demo.repository;

import com.example.demo.model.cottages.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface CottageRepository extends JpaRepository<Cottage, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Cottage c WHERE c.id = :id")
    @QueryHints({@QueryHint(name ="javax.persistance.lock.timeout",value="0")})
    Cottage findCottageById(Long id);
}
