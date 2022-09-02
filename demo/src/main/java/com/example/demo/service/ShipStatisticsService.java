package com.example.demo.service;

import java.util.Map;

public interface ShipStatisticsService {
    Map<String,Integer> numberOfReservationPerDaysInWeekForShip(Long id);

    Map<String,Integer> numberOfReservationPerMonthForShip(Long id);

    Map<String,Integer> numberOfReservationPerYearsForShip(Long id);
}
