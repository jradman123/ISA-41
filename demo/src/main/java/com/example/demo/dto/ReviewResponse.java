package com.example.demo.dto;

import com.example.demo.model.Review;
import lombok.Getter;

public class ReviewResponse {
    private String id;
    private String reservationId;
    private String comment;
    private String mark;
    private boolean unapproved;
    private boolean approved;

    public ReviewResponse() {
    }

    public ReviewResponse(String id, String reservationId, String comment, String mark, boolean unapproved, boolean approved) {
        this.id = id;
        this.reservationId = reservationId;
        this.comment = comment;
        this.mark = mark;
        this.unapproved = unapproved;
        this.approved = approved;
    }

    public ReviewResponse(Review review){
        id=review.getId().toString();
        reservationId = review.getReservation().getId().toString();
        mark = String.valueOf(review.getMark());
        comment = review.getComment();
        unapproved = review.isUnapproved();
        approved = review.isApproved();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public boolean isUnapproved() {
        return unapproved;
    }

    public void setUnapproved(boolean unapproved) {
        this.unapproved = unapproved;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
