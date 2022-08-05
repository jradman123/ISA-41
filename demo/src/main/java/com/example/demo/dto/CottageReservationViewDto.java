package com.example.demo.dto;


import com.example.demo.model.reservation.Reservation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CottageReservationViewDto {

    public Long id;
    public LocalDateTime resStart;
    public LocalDateTime resEnd;
    public Integer numberOfPerson;
    public  Double price;
    public String clientEmail;
    public String typeOfRes;
    public String objectId;


    public CottageReservationViewDto(Reservation r) {
        id=r.getId();
        resStart=r.getReservationStart();
        resEnd=r.getReservationEnd();
        numberOfPerson=r.getNumberOfPerson();
        price=r.getPrice();
        clientEmail=r.getRegisteredUser().getEmail();
        
    }
}
