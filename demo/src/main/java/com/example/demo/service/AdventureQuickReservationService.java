package com.example.demo.service;

import com.example.demo.model.adventures.AdventureQuickReservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AdventureQuickReservationService {
    String addNewQuickReservation(AdventureQuickReservation newAdventureQuickReservation);
    List<AdventureQuickReservation> findAllByAdventureId(int adventureId);
    AdventureQuickReservation deleteAdventureQuickReservation(Long id);
    boolean hasQuickReservation(int instructorId, LocalDateTime startTime, LocalDateTime endTime);
}
