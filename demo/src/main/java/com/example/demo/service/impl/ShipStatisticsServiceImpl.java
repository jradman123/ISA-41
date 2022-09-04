package com.example.demo.service.impl;

import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.ships.ShipReservation;
import com.example.demo.repository.CottageReservationRepository;
import com.example.demo.repository.ShipReservationRepository;
import com.example.demo.service.CottageService;
import com.example.demo.service.ShipService;
import com.example.demo.service.ShipStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ShipStatisticsServiceImpl implements ShipStatisticsService {
    @Autowired
    ShipReservationRepository shipReservationRepository;

    @Autowired
    ShipServiceImpl shipService;



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

    @Override
    public Map<String, Double> priceOfPeriod(String start, String end, Long id) {
        Map<String,Double> result = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime startDate=LocalDateTime.parse(start,formatter);
        LocalDateTime endDate=LocalDateTime.parse(end,formatter);
        List<ShipReservation> reservations = shipReservationRepository.getAllForShipInDateRange(startDate,endDate,id);
        List<ShipReservation> cancelled = shipReservationRepository.getAllCancelledForShipInDateRange(startDate,endDate,id);
        while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
            result.put(startDate.toLocalDate().toString(), countIncome(startDate,reservations) + countIncomeForCancelled(startDate,cancelled));
            startDate = startDate.plusDays(1);
        }

        return result;
    }
    private Double countIncomeForCancelled(LocalDateTime startDate, List<ShipReservation> reservations){
        Double income = 0.0;
        for (ShipReservation res : reservations) {
            if(res.getReservationStart().toLocalDate().isEqual(startDate.toLocalDate())) {
                Integer cancellation_condition = shipService.findShipById(res.getShip().getId()).getCancelationConditions();
                Double cc=Double.valueOf(cancellation_condition);
                income += (res.getPrice() * cc)/100;
            }
        }
        return income;
    }
    private Double countIncome(LocalDateTime startDate, List<ShipReservation> reservations){
        Double income = 0.0;
        for (ShipReservation res : reservations) {
            if(res.getReservationStart().toLocalDate().isEqual(startDate.toLocalDate())) {
                income += res.getPrice();
            }
        }
        return income;
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

    private Integer countReservationsForYear(Integer year, List<ShipReservation> reservations) {
        int i=0;
        for (ShipReservation r : reservations) {
            if (r.getReservationStart().getYear() == year) {
                i++;
            }
        }
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
