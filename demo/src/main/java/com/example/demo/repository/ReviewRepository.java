package com.example.demo.repository;

import com.example.demo.model.Review;
import com.example.demo.model.cottages.CottageAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
