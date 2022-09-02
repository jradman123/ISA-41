package com.example.demo.service.impl;

import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.repository.CottageReservationRepository;
import com.example.demo.service.CottageStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CottageStatisticsServiceImpl implements CottageStatisticsService {

    @Autowired
    CottageReservationRepository cottageReservationRepository;

    @Override
    public Map<String, Integer> numberOfReservationPerDaysInWeekForCottage(Long id) {
        Map<String,Integer> result = new HashMap<>();
        List<CottageReservation> reservations = cottageReservationRepository.getAllForCottage(id);
        for (DayOfWeek day : DayOfWeek.values()) {
            result.put(day.toString(),countReservationsForDays(day,reservations));
        }
        return result;
    }

    @Override
    public Map<String, Integer> numberOfReservationPerMonthForCottage(Long id) {
        Map<String,Integer> result = new HashMap<>();
        List<CottageReservation> reservations = cottageReservationRepository.getAllForCottage(id);
        for (Month month : Month.values()) {
            result.put(month.toString(),countReservationsForMonth(month,reservations));
        }
        return result;
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
