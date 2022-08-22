package com.example.demo.service;

import com.example.demo.model.adventures.AdventureQuickReservation;

import java.util.List;

public interface AdventureQuickReservationService {
    AdventureQuickReservation addNewQuickReservation(AdventureQuickReservation newAdventureQuickReservation);
    List<AdventureQuickReservation> findAllByAdventureId(int adventureId);
}
