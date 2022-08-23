package com.example.demo.dto;


import com.example.demo.model.reservation.Reservation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationViewDto {

    public Long id;
    public String resStart;
    public String resEnd;
    public Integer numberOfPerson;
    public  Double price;
    public String clientEmail;
    public String typeOfRes;
    public String objectId;
    public boolean  haveReport;


    public ReservationViewDto(Reservation r) {
        id=r.getId();
        resStart=r.getReservationStart().toString();
        resEnd=r.getReservationEnd().toString();
        numberOfPerson=r.getNumberOfPerson();
        price=r.getPrice();
        clientEmail=r.getRegisteredUser().getEmail();
        haveReport=r.isHaveReport();
    }
}
