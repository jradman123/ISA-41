package com.example.demo.mapper;

import com.example.demo.dto.DetailsAboutReservation;
import com.example.demo.model.adventures.AdventureReservation;
import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.ships.ShipReservation;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ReservationMapper {

    public DetailsAboutReservation fromAdventureReservation(AdventureReservation reservation){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DetailsAboutReservation details = new DetailsAboutReservation();
        details.setId(reservation.getId().toString());
        details.setClientEmail(reservation.getRegisteredUser().getEmail());
        details.setPrice(reservation.getPrice());
        details.setNameOfObject(reservation.getAdventure().getName());
        details.setNumberOfPerson(reservation.getNumberOfPerson());
        details.setResEnd(reservation.getReservationEnd().format(formatter));
        details.setResStart(reservation.getReservationStart().format(formatter));
        details.setClientName(reservation.getRegisteredUser().getFullName());
        return details;

    }

    public DetailsAboutReservation fromShipReservation(ShipReservation reservation){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DetailsAboutReservation details = new DetailsAboutReservation();
        details.setId(reservation.getId().toString());
        details.setClientEmail(reservation.getRegisteredUser().getEmail());
        details.setPrice(reservation.getPrice());
        details.setNameOfObject(reservation.getShip().getName());
        details.setNumberOfPerson(reservation.getNumberOfPerson());
        details.setResEnd(reservation.getReservationEnd().format(formatter));
        details.setResStart(reservation.getReservationStart().format(formatter));
        details.setClientName(reservation.getRegisteredUser().getFullName());
        return details;

    }

    public DetailsAboutReservation fromCottageReservation(CottageReservation reservation){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DetailsAboutReservation details = new DetailsAboutReservation();
        details.setId(reservation.getId().toString());
        details.setClientEmail(reservation.getRegisteredUser().getEmail());
        details.setPrice(reservation.getPrice());
        details.setNameOfObject(reservation.getCottage().getName());
        details.setNumberOfPerson(reservation.getNumberOfPerson());
        details.setResEnd(reservation.getReservationEnd().format(formatter));
        details.setResStart(reservation.getReservationStart().format(formatter));
        details.setClientName(reservation.getRegisteredUser().getFullName());
        return details;

    }
}
