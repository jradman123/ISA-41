package com.example.demo.service.impl;

import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureQuickReservation;
import com.example.demo.model.adventures.AdventureReservation;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.repository.AdventureQuickReservationRepository;
import com.example.demo.repository.AdventureReservationRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.AdventureService;
import com.example.demo.service.StatisticService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private AdventureReservationRepository adventureReservationRepository;

    @Autowired
    private AdventureQuickReservationRepository adventureQuickReservationRepository;

    @Autowired
    private AdventureService adventureService;

    @Override
    public Map<String, Integer> numberOfReservationPerDaysInWeekForAdventure(int id) {
        Map<String,Integer> result = new LinkedHashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            result.put(day.toString(),countReservationsForDayInLastWeek(day,id));
        }
        return result;
    }

    @Override
    public Map<String, Integer> numberOfReservationPerMonthForAdventure(int id) {
        Map<String,Integer> result = new LinkedHashMap<>();
        for (Month month : Month.values()) {
            result.put(month.toString(),countReservationsForMonthInLastYear(month,id));
        }
        return result;
    }

    @Override
    public Map<String, Integer> numberOfReservationPerYearForAdventure(int id) {
        Map<String,Integer> result = new LinkedHashMap<>();
        List<Integer> years = new ArrayList<>();
        for(int i=3; i>=0; i--) {
            years.add(LocalDateTime.now().getYear() - i);
        }
        for (Integer year : years) {
            result.put(year.toString(),countReservationsForDayInLastCoupleYears(year,id));
        }
        return result;
    }

    @Override
    public Map<String, Double> getIncomeForPeriod(String start, String end, int id) {
        Map<String,Double> result = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime startDate=LocalDateTime.parse(start,formatter);
        LocalDateTime endDate=LocalDateTime.parse(end,formatter);
        List<AdventureReservation> reservations = adventureReservationRepository.getAllForAdventureInDateRange(startDate,endDate,id);
        List<AdventureReservation> cancelled = adventureReservationRepository.getAllCancelledForAdventureInDateRange(startDate,endDate,id);
        while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
            result.put(startDate.toLocalDate().toString(), countIncome(startDate,reservations) + countIncomeForCancelled(startDate,cancelled));
            startDate = startDate.plusDays(1);
        }

        return result;
    }

    private Integer countReservationsForDayInLastWeek(DayOfWeek day,int id){
        List<AdventureReservation> reservations = adventureReservationRepository.getAllForAdventureInDateRange(LocalDateTime.now().minusDays(7), LocalDateTime.now(), id);
        int i=0;
        for (AdventureReservation r : reservations) {
            if (r.getReservationStart().getDayOfWeek().equals(day)) {
                i++;
            }
        }
        return i;
    }

    private Integer countReservationsForMonthInLastYear(Month month, int id){
        List<AdventureReservation> reservations = adventureReservationRepository.getAllForAdventureInDateRange(LocalDateTime.now().minusYears(1), LocalDateTime.now(), id);
        int i=0;
        for (AdventureReservation r : reservations) {
            if (r.getReservationStart().getMonth().equals(month)) {
                i++;
            }
        }
        return i;
    }

    private Integer countReservationsForDayInLastCoupleYears(int year,int id){
        List<AdventureReservation> reservations = adventureReservationRepository.getAllForAdventureInDateRange(LocalDateTime.now().minusYears(4), LocalDateTime.now(), id);
        int i=0;
        for (AdventureReservation r : reservations) {
            if (r.getReservationStart().getYear() == year) {
                i++;
            }
        }
        return i;
    }



    private Double countIncome(LocalDateTime startDate, List<AdventureReservation> reservations){
        Double income = 0.0;
        for (AdventureReservation res : reservations) {
            if(res.getReservationStart().toLocalDate().isEqual(startDate.toLocalDate())) {
                income += res.getPrice();
            }
        }
        return income;
    }

    private Double countIncomeForCancelled(LocalDateTime startDate, List<AdventureReservation> reservations){
        Double income = 0.0;
        for (AdventureReservation res : reservations) {
            if(res.getReservationStart().toLocalDate().isEqual(startDate.toLocalDate())) {
                Double cancellation_condition = adventureService.findAdventure(res.getAdventure().getId()).getCancellationConditions();
                income += (res.getPrice() * cancellation_condition)/100;
            }
        }
        return income;
    }



}
