package com.example.demo.repository;

import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.model.users.InstructorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CottageAvailabilityRepository  extends JpaRepository<CottageAvailability, Long> {

    @Query(value = "select * from cottage_availability where cottage_id = ?1", nativeQuery = true)
    List<CottageAvailability> getAllForCottage(Long cottageId);

}
