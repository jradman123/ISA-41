package com.example.demo.repository;

import com.example.demo.model.Review;
import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.model.users.InstructorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "select * from review where reservation_id = ?1", nativeQuery = true)
    List<Review> findAllForReservation(Long reservationId);
}
