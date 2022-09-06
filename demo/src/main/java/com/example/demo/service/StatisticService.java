package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface StatisticService {

    Map<String,Integer> numberOfReservationPerDaysInWeekForAdventure(int id);
    Map<String,Integer> numberOfReservationPerMonthForAdventure(int id);
    Map<String,Integer> numberOfReservationPerYearForAdventure(int id);
    Map<String,Double> getIncomeForPeriod(String start,String end,int id);
    Map<String,Double> getIncomeForWebsiteInPeriod(String start, String end);
}
