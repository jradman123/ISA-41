package com.example.demo.service;

import org.springframework.http.HttpStatus;

import java.util.Map;

public interface CottageStatisticsService {

    Map<String,Integer> numberOfReservationPerDaysInWeekForCottage(Long id);

    Map<String,Integer> numberOfReservationPerMonthForCottage(Long id);

    Map<String,Integer> numberOfReservationPerYearsForCottage(Long id);






}
