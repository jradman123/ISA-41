package com.example.demo.service.impl;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.repository.CottageReservationRepository;
import com.example.demo.service.CottageService;
import com.example.demo.service.CottageStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CottageStatisticsServiceImpl implements CottageStatisticsService {

    @Autowired
    CottageReservationRepository cottageReservationRepository;

    @Autowired
    CottageService cottageService;

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

    @Override
    public Map<String, Double> priceOfPeriod(String start, String end,Long id) {
        Map<String,Double> result = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime startDate=LocalDateTime.parse(start,formatter);
        LocalDateTime endDate=LocalDateTime.parse(end,formatter);

        List<CottageReservation> reservations = cottageReservationRepository.getAllForCottage(id);


        List<CottageReservation> cancelled = cottageReservationRepository.getAllCancelledForCottageInDateRange(startDate,endDate,id);
        while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
            result.put(startDate.toLocalDate().toString(), countIncome(startDate,reservations) + countIncomeForCancelled(startDate,cancelled));
            startDate = startDate.plusDays(1);
        }

        return result;


    }

    private Double countIncomeForCancelled(LocalDateTime startDate, List<CottageReservation> reservations){
        Double income = 0.0;
        for (CottageReservation res : reservations) {
            if(res.getReservationStart().toLocalDate().isEqual(startDate.toLocalDate())) {
                Integer cancellation_condition = cottageService.findCottageById(res.getCottage().getId()).getCancelationConditions();
                Double cc=Double.valueOf(cancellation_condition);
                income += (res.getPrice() * cc)/100;
            }
        }
        return income;
    }
    private Double countIncome(LocalDateTime startDate, List<CottageReservation> reservations){
        Double income = 0.0;
        for (CottageReservation res : reservations) {
            if(res.getReservationStart().toLocalDate().isEqual(startDate.toLocalDate())) {
                income += res.getPrice();
            }
        }
        return income;
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
