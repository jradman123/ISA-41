package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.model.adventures.AdventureUtility;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageQuickReservation;
import com.example.demo.model.cottages.CottageUtility;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.ships.ShipQuickReservation;
import com.example.demo.model.ships.ShipUtility;
import com.example.demo.model.users.RegisteredUser;
import com.example.demo.model.users.User;
import com.example.demo.repository.*;
import com.example.demo.service.CottageService;
import com.example.demo.service.ShipQuickReservationService;
import com.example.demo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShipQuickReservationServiceImpl implements ShipQuickReservationService {

    @Autowired
    private ShipQuickReservationRepository shipQuickReservationRepository;

    @Autowired
    private ShipService shipService;

    @Autowired
    private EmailSenderServiceImpl emailSenderService;

    @Autowired
    private UserServiceImpl userService;


    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private ShipUtilityRepository shipUtilityRepository;

    @Autowired
    private ShipRepository shipRepository;



    @Override
    @Transactional
    public ShipQuickReservation createAppointment(ShipQuickReservationDto dto) {

        ShipQuickReservation appointment = new ShipQuickReservation();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        LocalDateTime start=LocalDateTime.parse(dto.getStartTime(), formatter);
        LocalDateTime end=LocalDateTime.parse(dto.getEndTime(), formatter);

        appointment.setStartTime(start);
        appointment.setEndTime(end);



        boolean reservations=this.reservationService.checkDatesForShip(start,end,dto.getShipId());
        boolean availability=this.reservationService.checkAvailabilityForShip(start,end,dto.getShipId());
        boolean app=this.checkApp(start,end,dto.getShipId());

        if(reservations && availability && app) {

            appointment.setGuestLimit(Integer.parseInt(dto.getGuestLimit()));
        appointment.setPrice(Double.parseDouble(dto.getPrice()));
        appointment.setValidUntil(LocalDateTime.parse(dto.getValidUntil(), formatter));

        Set<ShipUtility> utilities = new HashSet<>();
        for (ResponseUtility responseUtility : dto.getUtilities()) {
            utilities.add(shipUtilityRepository.findById(Long.parseLong(responseUtility.getId())).get());
        }
        appointment.setShipUtilities(utilities);


        if (dto.getShipId() != null) {
            Ship ship = shipRepository.findShipById(Long.parseLong(dto.getShipId()));
            appointment.setShip(ship);
            Set<RegisteredUser> subscribers = ship.getSubscribers();
            for (RegisteredUser client : subscribers) {
                notifyUserForCottage(client.getEmail(), appointment);
            }
            shipQuickReservationRepository.save(appointment);


            return appointment;

        }else {return null;}
        }
        return null;
    }

    public boolean checkApp(LocalDateTime startDate,LocalDateTime endDate,String objectId) {
        for (ShipQuickReservation ct : shipQuickReservationRepository.getAllForShip(Long.parseLong(objectId))) {

            if (startDate.equals(ct.getStartTime()) ||
                    startDate.equals(ct.getEndTime()) ||

                    endDate.equals(ct.getEndTime()) ||
                    endDate.equals(ct.getStartTime())  ||
                    (startDate.isAfter(ct.getStartTime()) && startDate.isBefore(ct.getEndTime()) && endDate.isAfter(ct.getEndTime()))
                    || (endDate.isAfter(ct.getStartTime()) && endDate.isBefore(ct.getEndTime()))
                    || (startDate.isBefore(ct.getStartTime()) && endDate.isAfter(ct.getEndTime())) ||
                    (startDate.isAfter(ct.getStartTime()) && endDate.isBefore(ct.getEndTime())))
            {
                return false;

            }
        }
        return true;
    }

    public void notifyUserForCottage(String email,ShipQuickReservation reservation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        emailSenderService.sendEmail(email,"Akcija!","Za vikendicu " + reservation.getShip().getName() +
                " definisana je nova akcija.Za " + reservation.getPrice() + " € rezervišite termin za "  + reservation.getGuestLimit() + " osoba.Termin rezervacije je definisan od " +
                reservation.getStartTime().format(formatter) + " do " + reservation.getEndTime().format(formatter) + ".Akcija važi do " +
                reservation.getValidUntil().format(formatter) + ".Sve detalje možete pogledati na našem sajtu.");
    }
    @Override
    public ResponseEntity<Long> deleteApp(Long id) {
        List<ShipQuickReservation> appointments = this.shipQuickReservationRepository.findAll();
        for (ShipQuickReservation appointment : appointments) {
            if (appointment.getId() == id) {
                appointment.setDeleted(true);
                shipQuickReservationRepository.save(appointment);
            }

        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    @Override
    public List<ShipQuickReservationResponse> findApp(Long id) {
        List<ShipQuickReservationResponse> appointmentDtos = new ArrayList<>();
        for (ShipQuickReservation appointment : shipQuickReservationRepository.findAll()) {
            if (appointment.getShip() != null & appointment.isDeleted() == false && appointment.getValidUntil().isAfter(LocalDateTime.now())) {
                if (id.equals(appointment.getShip().getId())) {

                    ShipQuickReservationResponse shipQuickReservationResponse=new ShipQuickReservationResponse();
                    shipQuickReservationResponse.setReserved(appointment.isReserved());
                    shipQuickReservationResponse.setShipId(appointment.getShip().getId().toString());
                    shipQuickReservationResponse.setStartTime(appointment.getStartTime().toString());
                    shipQuickReservationResponse.setEndTime(appointment.getEndTime().toString());
                    shipQuickReservationResponse.setValidUntil(appointment.getValidUntil().toString());
                    shipQuickReservationResponse.setPrice(appointment.getPrice().toString());
                    shipQuickReservationResponse.setGuestLimit(String.valueOf(appointment.getGuestLimit()));
                    shipQuickReservationResponse.setId(appointment.getId().toString());
                    Set<ResponseUtility>  responseUtilities= new HashSet<>();
                    for (ShipUtility utility: appointment.getShipUtilities()) {
                        responseUtilities.add(new ResponseUtility(utility.getId().toString(),utility.getUtility().getName(),utility.getPrice().toString()));
                    }

                    shipQuickReservationResponse.setUtilities(responseUtilities);

                    appointmentDtos.add(shipQuickReservationResponse);
                }
            }
        }
        return appointmentDtos;
    }
}
