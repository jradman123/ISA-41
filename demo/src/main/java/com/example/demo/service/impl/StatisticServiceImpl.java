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
import java.time.Month;
import java.time.Year;
import java.util.*;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private AdventureReservationRepository adventureReservationRepository;


    @Override
    public Map<String, Integer> numberOfReservationPerDaysInWeekForAdventure(int id) {
        Map<String,Integer> result = new LinkedHashMap<>();
        List<AdventureReservation> reservations = adventureReservationRepository.getAllForAdventureInDateRange(LocalDateTime.now().minusDays(7), LocalDateTime.now(), id);
        for (DayOfWeek day : DayOfWeek.values()) {
            result.put(day.toString(),countReservationsForDayInLastWeek(day,reservations));
        }
        return result;
    }

    @Override
    public Map<String, Integer> numberOfReservationPerMonthForAdventure(int id) {
        Map<String,Integer> result = new LinkedHashMap<>();
        List<AdventureReservation> reservations = adventureReservationRepository.getAllForAdventureInDateRange(LocalDateTime.now().minusYears(1), LocalDateTime.now(), id);
        for (Month month : Month.values()) {
            result.put(month.toString(),countReservationsForMonthInLastYear(month,reservations));
        }
        return result;
    }

    @Override
    public Map<String, Integer> numberOfReservationPerYearForAdventure(int id) {
        Map<String,Integer> result = new LinkedHashMap<>();
        List<AdventureReservation> reservations = adventureReservationRepository.getAllForAdventureInDateRange(LocalDateTime.now().minusYears(4), LocalDateTime.now(), id);
        List<Integer> years = new ArrayList<>();
        for(int i=3; i>=0; i--) {
            years.add(LocalDateTime.now().getYear() - i);
        }
        for (Integer year : years) {
            result.put(year.toString(),countReservationsForDayInLastCoupleYears(year,reservations));
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

    private Integer countReservationsForMonthInLastYear(Month month, List<AdventureReservation> reservations){
        int i=0;
        for (AdventureReservation r : reservations) {
            if (r.getReservationStart().getMonth().equals(month)) {
                i++;
            }
        }
        return i;
    }

    private Integer countReservationsForDayInLastCoupleYears(int year, List<AdventureReservation> reservations){
        int i=0;
        for (AdventureReservation r : reservations) {
            if (r.getReservationStart().getYear() == year) {
                i++;
            }
        }
        return i;
    }
}
