package com.example.demo.service.impl;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.repository.CottageReservationRepository;
import com.example.demo.service.CottageStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

@Service
public class CottageStatisticsServiceImpl implements CottageStatisticsService {

    @Autowired
    CottageReservationRepository cottageReservationRepository;

    @Override
    public Map<String, Integer> numberOfReservationPerDaysInWeekForCottage(Long id) {
        Map<String,Integer> result = new LinkedHashMap<>();
        List<CottageReservation> reservations = cottageReservationRepository.getAllForCottage(id);
        for (DayOfWeek day : DayOfWeek.values()) {
            result.put(day.toString(),countReservationsForDays(day,reservations));
        }
        return result;
    }

    @Override
    public Map<String, Integer> numberOfReservationPerMonthForCottage(Long id) {
        Map<String,Integer> result = new LinkedHashMap<>();
        List<CottageReservation> reservations = cottageReservationRepository.getAllForCottage(id);
        for (Month month : Month.values()) {
            result.put(month.toString(),countReservationsForMonth(month,reservations));
        }
        return result;
    }

    @Override
    public Map<String, Integer> numberOfReservationPerYearsForCottage(Long id) {
        Map<String,Integer> result = new LinkedHashMap<>();
        List<CottageReservation> reservations = cottageReservationRepository.getAllForCottageInDateRange(LocalDateTime.now().minusYears(4), LocalDateTime.now(), id);
        List<Integer> years = new ArrayList<>();
        for(int i=3; i>=0; i--) {
            years.add(LocalDateTime.now().getYear() - i);
        }
        for (Integer year : years) {
            result.put(year.toString(),countReservationsForDayInLastCoupleYears(year,reservations));
        }
        return result;



    }

    private Integer countReservationsForDayInLastCoupleYears(Integer year, List<CottageReservation> reservations) {
        int i=0;
        for (CottageReservation r : reservations) {
            if (r.getReservationStart().getYear() == year) {
                i++;
            }
        }
        return i;
    }

    private Integer countReservationsForMonth(Month month, List<CottageReservation> reservations) {
        int i=0;
        for (CottageReservation r : reservations) {
            if (r.getReservationStart().getMonth().equals(month)) {
                i++;
            }
        }
        return i;
    }

 

    private Integer countReservationsForDays(DayOfWeek day,List<CottageReservation> reservations){
        int i=0;
        for (CottageReservation r : reservations) {
            if (r.getReservationStart().getDayOfWeek().equals(day)) {
                i++;
            }
        }
        return i;
    }
}
