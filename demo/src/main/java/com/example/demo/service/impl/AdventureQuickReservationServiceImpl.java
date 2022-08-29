package com.example.demo.service.impl;

import com.example.demo.model.adventures.AdventureQuickReservation;
import com.example.demo.model.users.RegisteredUser;
import com.example.demo.repository.AdventureQuickReservationRepository;
import com.example.demo.service.AdventureQuickReservationService;
import com.example.demo.service.AdventureService;
import com.example.demo.service.EmailSenderService;
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

    @Override
    public AdventureQuickReservation addNewQuickReservation(AdventureQuickReservation newAdventureQuickReservation) {
        AdventureQuickReservation saved = adventureQuickReservationRepository.save(newAdventureQuickReservation);
        Set<RegisteredUser> subscribers = adventureService.findAdventure(newAdventureQuickReservation.getAdventure().getId()).getSubscribers();
        for (RegisteredUser client : subscribers) {
            notifySubscribers(client.getEmail(),newAdventureQuickReservation);
        }
        return saved;
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

    private void notifySubscribers(String email,AdventureQuickReservation reservation){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        emailSenderService.sendEmail(email,"Akcija!","Za avanturu " + reservation.getAdventure().getName() +
                " definisana je nova akcija.Za " + reservation.getPrice() + " € rezervišite termin za "  + reservation.getGuestLimit() + " osoba.Termin rezervacije je definisan od " +
                reservation.getStartTime().format(formatter) + " do " + reservation.getEndTime().format(formatter) + ".Akcija važi do " +
                reservation.getValidUntil().format(formatter) + ".Sve detalje možete pogledati na našem sajtu.");
    }

}
