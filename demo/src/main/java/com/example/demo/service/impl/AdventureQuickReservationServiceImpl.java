package com.example.demo.service.impl;

import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureQuickReservation;
import com.example.demo.model.users.RegisteredUser;
import com.example.demo.repository.AdventureQuickReservationRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AdventureQuickReservationServiceImpl implements AdventureQuickReservationService {

    @Autowired
    private AdventureQuickReservationRepository adventureQuickReservationRepository;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private InstructorAvailabilityService instructorAvailabilityService;

    @Autowired
    private ReservationService reservationService;

    @Override
    public String addNewQuickReservation(AdventureQuickReservation newAdventureQuickReservation) {
        if(canBeAdded(newAdventureQuickReservation)) {
            adventureQuickReservationRepository.save(newAdventureQuickReservation);
            Set<RegisteredUser> subscribers = adventureService.findAdventure(newAdventureQuickReservation.getAdventure().getId()).getSubscribers();
            for (RegisteredUser client : subscribers) {
                notifySubscribers(client.getEmail(), newAdventureQuickReservation);
            }
            return "Success!";
        }
        return "Date range is not free!";
    }

    private boolean canBeAdded(AdventureQuickReservation newAdventureQuickReservation){
        boolean isInstructorAvailable = instructorAvailabilityService.isInstructorAvailable(newAdventureQuickReservation.getAdventure().getInstructor().getId(),newAdventureQuickReservation.getStartTime(),newAdventureQuickReservation.getEndTime());
        boolean hasInstructorReservations  = reservationService.hasInstructorReservationsForRange(newAdventureQuickReservation.getAdventure().getInstructor().getId(),newAdventureQuickReservation.getStartTime(),newAdventureQuickReservation.getEndTime());
        boolean hasQuickReservations = hasQuickReservation(newAdventureQuickReservation.getAdventure().getInstructor().getId(),newAdventureQuickReservation.getStartTime(),newAdventureQuickReservation.getEndTime());
        if(isInstructorAvailable && !hasInstructorReservations && !hasQuickReservations) {
            return true;
        }
        return false;
    }
    @Override
    public List<AdventureQuickReservation> findAllByAdventureId(int adventureId) {
        List<AdventureQuickReservation> reservations = new ArrayList<>();
        for (AdventureQuickReservation reservation : adventureQuickReservationRepository.findAllByAdventureId(adventureId)) {
            if(reservation.getStartTime().isAfter(LocalDateTime.now()) && !reservation.isDeleted()){
                reservations.add(reservation);
            }
        }

        return reservations;
    }

    @Override
    public AdventureQuickReservation deleteAdventureQuickReservation(Long id) {
        AdventureQuickReservation reservation = adventureQuickReservationRepository.findById(id).get();
        reservation.setDeleted(true);
        return adventureQuickReservationRepository.save(reservation);
    }

    @Override
    public boolean hasQuickReservation(int instructorId, LocalDateTime startTime, LocalDateTime endTime) {
        for(Adventure adventure : adventureService.getAllForInstructor(instructorId)){
            for(AdventureQuickReservation quickReservation : adventureQuickReservationRepository.findAll()){
                if(quickReservation.getAdventure().getId() == adventure.getId()){
                    if((startTime.isAfter(quickReservation.getStartTime()) && endTime.isBefore(quickReservation.getEndTime())) ||
                            (startTime.isBefore(quickReservation.getStartTime()) && endTime.isAfter(quickReservation.getEndTime())) ||
                            (startTime.isBefore(quickReservation.getStartTime()) && endTime.isBefore(quickReservation.getEndTime()) && endTime.isAfter(quickReservation.getStartTime())) ||
                            (startTime.isBefore(quickReservation.getEndTime()) && endTime.isAfter(quickReservation.getEndTime()) && startTime.isAfter(quickReservation.getStartTime()))){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void notifySubscribers(String email,AdventureQuickReservation reservation){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        emailSenderService.sendEmail(email,"Akcija!","Za avanturu " + reservation.getAdventure().getName() +
                " definisana je nova akcija.Za " + reservation.getPrice() + " € rezervišite termin za "  + reservation.getGuestLimit() + " osoba.Termin rezervacije je definisan od " +
                reservation.getStartTime().format(formatter) + " do " + reservation.getEndTime().format(formatter) + ".Akcija važi do " +
                reservation.getValidUntil().format(formatter) + ".Sve detalje možete pogledati na našem sajtu.");
    }



}
