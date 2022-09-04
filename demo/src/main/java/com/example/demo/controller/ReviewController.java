package com.example.demo.controller;


import com.example.demo.dto.ReportDto;
import com.example.demo.dto.ReviewResponse;
import com.example.demo.model.Report;
import com.example.demo.model.Review;
import com.example.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(value = "/all-unseen")
    public ResponseEntity<List<ReviewResponse>> getAllUnseen() {
        List<ReviewResponse> response = new ArrayList<>();
        for (Review review : reviewService.findAllUnseen()) {
            response.add(new ReviewResponse(review));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/approve/{id}")
    public ResponseEntity<List<ReviewResponse>> approveReview(@PathVariable Long id){
        List<ReviewResponse> response = new ArrayList<>();
        for (Review review : reviewService.approveReview(id)) {
            response.add(new ReviewResponse(review));
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/reject/{id}")
    public ResponseEntity<List<ReviewResponse>> rejectReview(@PathVariable Long id){
        List<ReviewResponse> response = new ArrayList<>();
        for (Review review : reviewService.rejectReview(id)) {
            response.add(new ReviewResponse(review));
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
