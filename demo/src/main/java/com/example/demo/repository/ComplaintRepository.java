package com.example.demo.repository;

import com.example.demo.model.Complaint;
import com.example.demo.model.cottages.CottageAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
    
    @Query(value = "select * from complaint where answered = false", nativeQuery = true)
    List<Complaint> getAllUnanswered();

}
