package com.example.demo.service.impl;

import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.ships.ShipReservation;
import com.example.demo.repository.CottageReservationRepository;
import com.example.demo.repository.ShipReservationRepository;
import com.example.demo.service.ShipStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.*;

@Service
public class ShipStatisticsServiceImpl implements ShipStatisticsService {
    @Autowired
    ShipReservationRepository shipReservationRepository;


    @Override
    public Map<String, Integer> numberOfReservationPerDaysInWeekForShip(Long id) {
        Map<String,Integer> result = new LinkedHashMap<>();
        List<ShipReservation> reservations = shipReservationRepository.getAllForShip(id);
        for (DayOfWeek day : DayOfWeek.values()) {
            result.put(day.toString(),countReservationsForDays(day,reservations));
        }
        return result;
    }

    @Override
    public Map<String, Integer> numberOfReservationPerMonthForShip(Long id) {
        Map<String,Integer> result = new LinkedHashMap<>();
        List<ShipReservation> reservations = shipReservationRepository.getAllForShip(id);
        for (Month month : Month.values()) {
            result.put(month.toString(),countReservationsForMonth(month,reservations));
        }
        return result;
    }

    @Override
    public Map<String, Integer> numberOfReservationPerYearsForShip(Long id) {
        Map<String,Integer> result = new LinkedHashMap<>();
        List<ShipReservation> reservations = shipReservationRepository.getAllForShipInDateRange(LocalDateTime.now().minusYears(4), LocalDateTime.now(), id);
        List<Integer> years = new ArrayList<>();
        for(int i=3; i>=0; i--) {
            years.add(LocalDateTime.now().getYear() - i);
        }
        for (Integer year : years) {
            result.put(year.toString(),countReservationsForDayInLastCoupleYears(year,reservations));
        }
        return result;
    }

    private Integer countReservationsForDayInLastCoupleYears(Integer year, List<ShipReservation> reservations) {
        int i=0;
        for (ShipReservation r : reservations) {
            if (r.getReservationStart().getYear() == year) {
                i++;
            }
        }
        return i;
    }

    private Integer countReservationsForMonth(Month month, List<ShipReservation> reservations) {
        int i=0;
        for (ShipReservation r : reservations) {
            if (r.getReservationStart().getMonth().equals(month)) {
                i++;
            }
        }
        return i;
    }

    private Integer countReservationsForYear(Year year, List<ShipReservation> reservations) {
        int i=0;

        return i;
    }

    private Integer countReservationsForDays(DayOfWeek day,List<ShipReservation> reservations){
        int i=0;
        for (ShipReservation r : reservations) {
            if (r.getReservationStart().getDayOfWeek().equals(day)) {
                i++;
            }
        }
        return i;
    }
}
