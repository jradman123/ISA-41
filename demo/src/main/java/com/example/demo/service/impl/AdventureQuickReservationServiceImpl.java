package com.example.demo.service.impl;

import com.example.demo.model.adventures.AdventureQuickReservation;
import com.example.demo.repository.AdventureQuickReservationRepository;
import com.example.demo.service.AdventureQuickReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdventureQuickReservationServiceImpl implements AdventureQuickReservationService {

    @Autowired
    private AdventureQuickReservationRepository adventureQuickReservationRepository;

    @Override
    public AdventureQuickReservation addNewQuickReservation(AdventureQuickReservation newAdventureQuickReservation) {
        return adventureQuickReservationRepository.save(newAdventureQuickReservation);
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
}
