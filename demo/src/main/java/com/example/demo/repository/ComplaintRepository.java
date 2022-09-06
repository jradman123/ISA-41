package com.example.demo.repository;

import com.example.demo.model.Complaint;
import com.example.demo.model.cottages.CottageAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
    
    @Query(value = "select * from complaint where answered = false", nativeQuery = true)
    List<Complaint> getAllUnanswered();

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query(value = "select c from Complaint c where c.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    Complaint findComplaintById(@Param("id") Long id);
}
