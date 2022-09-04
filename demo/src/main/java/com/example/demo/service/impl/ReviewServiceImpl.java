package com.example.demo.service.impl;

import com.example.demo.model.Review;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.repository.AdventureReservationRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ReservationService reservationService;

    @Override
    public List<Review> findAllForReservation(Long id) {
        return reviewRepository.findAllForReservation(id);
    }

    @Override
    public List<Review> findAllApprovedForReservation(Long id) {
        return reviewRepository.findAllApprovedForReservation(id);
    }

    @Override
    public List<Review> findAllUnseen() {
        return reviewRepository.findAllUnseen();
    }

    @Override
    public List<Review> approveReview(Long id) {
        Review approved = reviewRepository.findById(id).get();
        Reservation reservation = reservationRepository.findById(approved.getReservation().getId()).get();
        approved.setApproved(true);
        reviewRepository.save(approved);
        notifyOwner(approved,reservation);
        return findAllUnseen();
    }

    private void notifyOwner(Review approved, Reservation reservation) {
        String ownersEmail = reservationService.getOwnersEmail(reservation);
        emailSenderService.sendEmail(ownersEmail,"Recenzija prihvaćena!","Komentar : "+approved.getComment() +
                "\nOcjena : " + approved.getMark() + " \nRecenzija za vašu rezervaciju je prihvaćena.");
    }


    @Override
    public List<Review> rejectReview(Long id) {
        Review approved = reviewRepository.findById(id).get();
        approved.setUnapproved(true);
        reviewRepository.save(approved);
        return findAllUnseen();
    }

}
