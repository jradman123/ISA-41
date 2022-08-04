package com.example.demo.dto;

import com.example.demo.model.reservation.Reservation;

import java.time.LocalDateTime;

public class ReservationDto {

    public Long id;
    public LocalDateTime resStart;
    public LocalDateTime resEnd;
    public Integer numberOFPerson;
    public Double price;
    public Integer cottageId;
    public Integer shipId;
    public Integer adventureId;
    public String clientEmail;



    public ReservationDto(Reservation reservation) {
    }
}
