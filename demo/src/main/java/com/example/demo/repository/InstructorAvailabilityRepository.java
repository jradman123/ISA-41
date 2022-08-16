package com.example.demo.repository;

import com.example.demo.model.users.InstructorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface InstructorAvailabilityRepository extends JpaRepository<InstructorAvailability, Long> {

    @Query(value = "select * from instructor_availability where instructor_id = ?1", nativeQuery = true)
    List<InstructorAvailability> getAllForInstructor(int instructorId);
}
