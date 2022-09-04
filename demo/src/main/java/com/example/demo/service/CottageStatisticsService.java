package com.example.demo.service;

import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public interface CottageStatisticsService {

    Map<String,Integer> numberOfReservationPerDaysInWeekForCottage(Long id);

    Map<String,Integer> numberOfReservationPerMonthForCottage(Long id);

    Map<String,Integer> numberOfReservationPerYearsForCottage(Long id);


    Map<String,Double> priceOfPeriod(String start, String end, Long id);
}
