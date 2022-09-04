package com.example.demo.service;

import com.example.demo.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllForReservation(Long id);
    List<Review> findAllApprovedForReservation(Long id);
    List<Review> findAllUnseen();
    List<Review> approveReview(Long id);
    List<Review> rejectReview(Long id);
}
