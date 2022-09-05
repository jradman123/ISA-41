package com.example.demo.repository;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CottageRepository extends JpaRepository<Cottage, Long> {
    Cottage findById(String id);
    @Query(value = "select * from cottage where is_deleted = false", nativeQuery = true)
    List<Cottage> getAllUndeleted();
}
