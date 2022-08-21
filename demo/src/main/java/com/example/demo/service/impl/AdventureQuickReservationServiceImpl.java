package com.example.demo.service.impl;

import com.example.demo.model.adventures.AdventureQuickReservation;
import com.example.demo.repository.AdventureQuickReservationRepository;
import com.example.demo.service.AdventureQuickReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdventureQuickReservationServiceImpl implements AdventureQuickReservationService {

    @Autowired
    private AdventureQuickReservationRepository adventureQuickReservationRepository;

    @Override
    public AdventureQuickReservation addNewQuickReservation(AdventureQuickReservation newAdventureQuickReservation) {
        return adventureQuickReservationRepository.save(newAdventureQuickReservation);
    }
}
