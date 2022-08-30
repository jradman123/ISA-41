package com.example.demo.service.impl;

import com.example.demo.dto.CottageQuickReservationDto;
import com.example.demo.dto.CottageQuickReservationResponse;
import com.example.demo.dto.ResponseUtility;
import com.example.demo.model.adventures.AdventureUtility;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageQuickReservation;
import com.example.demo.model.cottages.CottageUtility;
import com.example.demo.model.reservation.Appointment;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.ships.ShipUtility;
import com.example.demo.model.users.RegisteredUser;
import com.example.demo.model.users.User;
import com.example.demo.repository.CottageQuickReservationRepository;
import com.example.demo.repository.CottageUtilityRepository;
import com.example.demo.service.CottageQuickReservationService;
import com.example.demo.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CottageQuickReservationServiceImpl implements CottageQuickReservationService {

    @Autowired
    private CottageService cottageService;

    @Autowired
    private CottageQuickReservationRepository cottageQuickReservationRepository;

    @Autowired
    private EmailSenderServiceImpl emailSenderService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CottageUtilityRepository cottageUtilityRepository;


    @Override
    public List<CottageQuickReservationResponse> findApp(Long id) {
        List<CottageQuickReservationResponse> appointmentDtos = new ArrayList<>();
        for (CottageQuickReservation appointment : cottageQuickReservationRepository.findAll()) {
            if (appointment.getCottage() != null & appointment.isDeleted() == false && appointment.getValidUntil().isAfter(LocalDateTime.now())) {
                if (id.equals(appointment.getCottage().getId())) {

                    CottageQuickReservationResponse cottageQuickReservationResponse=new CottageQuickReservationResponse();
                    cottageQuickReservationResponse.setReserved(appointment.isReserved());
                    cottageQuickReservationResponse.setCottageId(appointment.getCottage().getId().toString());
                    cottageQuickReservationResponse.setStartTime(appointment.getStartTime().toString());
                    cottageQuickReservationResponse.setEndTime(appointment.getEndTime().toString());
                    cottageQuickReservationResponse.setValidUntil(appointment.getValidUntil().toString());
                    cottageQuickReservationResponse.setPrice(appointment.getPrice().toString());
                    cottageQuickReservationResponse.setGuestLimit(String.valueOf(appointment.getGuestLimit()));
                    cottageQuickReservationResponse.setId(appointment.getId().toString());
                    Set<ResponseUtility>  responseUtilities= new HashSet<>();
                    for (CottageUtility utility: appointment.getCottageUtilities()) {
                        responseUtilities.add(new ResponseUtility(utility.getId().toString(),utility.getUtility().getName(),utility.getPrice().toString()));
                    }

                    cottageQuickReservationResponse.setUtilities(responseUtilities);

                    appointmentDtos.add(cottageQuickReservationResponse);


                    appointmentDtos.add(cottageQuickReservationResponse);
                }
            }
        }
        return appointmentDtos;
    }

    @Override
    public CottageQuickReservation createAppointment(CottageQuickReservationDto dto) {
        CottageQuickReservation appointment = new CottageQuickReservation();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        appointment.setStartTime(LocalDateTime.parse(dto.getStartTime(), formatter));
        appointment.setEndTime(LocalDateTime.parse(dto.getEndTime(), formatter));
        appointment.setGuestLimit(Integer.parseInt(dto.getGuestLimit()));
        appointment.setPrice(Double.parseDouble(dto.getPrice()));
        appointment.setValidUntil(LocalDateTime.parse(dto.getValidUntil(), formatter));

        Set<CottageUtility> utilities = new HashSet<>();
        for (ResponseUtility responseUtility : dto.getUtilities()) {
            utilities.add(cottageUtilityRepository.findById(Long.parseLong(responseUtility.getId())).get());
        }
        appointment.setCottageUtilities(utilities);


        if (dto.getCottageId() != null) {
            Cottage cottage = cottageService.findCottageById(Long.parseLong(dto.getCottageId()));
            appointment.setCottage(cottage);

            Set<RegisteredUser> subscribers = cottage.getSubscribers();
            for (RegisteredUser client : subscribers) {
                notifyUserForCottage(client.getEmail(), appointment);
            }
            cottageQuickReservationRepository.save(appointment);





            return appointment;


        }

        return null;
    }

    public void notifyUserForCottage(String email, CottageQuickReservation reservation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        emailSenderService.sendEmail(email,"Akcija!","Za vikendicu " + reservation.getCottage().getName() +
                " definisana je nova akcija.Za " + reservation.getPrice() + " € rezervišite termin za "  + reservation.getGuestLimit() + " osoba.Termin rezervacije je definisan od " +
                reservation.getStartTime().format(formatter) + " do " + reservation.getEndTime().format(formatter) + ".Akcija važi do " +
                reservation.getValidUntil().format(formatter) + ".Sve detalje možete pogledati na našem sajtu.");
    }    

    @Override
    public ResponseEntity<Long> deleteApp(Long id) {
        List<CottageQuickReservation> appointments = this.cottageQuickReservationRepository.findAll();
        for (CottageQuickReservation appointment : appointments) {
            if (appointment.getId() == id) {
                appointment.setDeleted(true);
                cottageQuickReservationRepository.save(appointment);
            }

        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

}