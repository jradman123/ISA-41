package com.example.demo.service.impl;

import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureReservation;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.repository.AdventureReservationRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.StatisticService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private AdventureReservationRepository adventureReservationRepository;


    @Override
    public Map<String, Integer> numberOfReservationPerDaysInWeekForAdventure(int id) {
        Map<String,Integer> result = new HashMap<>();
        List<AdventureReservation> reservations = adventureReservationRepository.getAllInForAdventureLastWeek(LocalDateTime.now().minusDays(7), LocalDateTime.now(), id);
        for (DayOfWeek day : DayOfWeek.values()) {
            result.put(day.toString(),countReservationsForDayInLastWeek(day,reservations));
        }
        return result;
    }

    private Integer countReservationsForDayInLastWeek(DayOfWeek day,List<AdventureReservation> reservations){
        int i=0;
        for (AdventureReservation r : reservations) {
            if (r.getReservationStart().getDayOfWeek().equals(day)) {
                i++;
            }
        }
        return i;
    }
}
