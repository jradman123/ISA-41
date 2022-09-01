package com.example.demo.service.impl;

import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureReservation;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.repository.AdventureReservationRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.StatisticService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private AdventureReservationRepository adventureReservationRepository;


    @Override
    public List<Integer> numberOfReservationPerDaysInWeekForInstructor(int id) {
        List<Integer> result = new ArrayList<Integer>();
        int monday =0;
        int tuesday = 0;
        int wednesday = 0;
        int thursday = 0;
        int friday = 0;
        int saturday = 0;
        int sunday = 0;
        List<AdventureReservation> reservations =adventureReservationRepository.getAllInForInstructorLastWeek(LocalDateTime.now().minusDays(7), LocalDateTime.now(),id);
        for (AdventureReservation r : reservations) {
            if(r.getReservationStart().getDayOfWeek().equals(DayOfWeek.MONDAY)){
                monday++;
            }
            if(r.getReservationStart().getDayOfWeek().equals(DayOfWeek.TUESDAY)){
                tuesday++;
            }
            if(r.getReservationStart().getDayOfWeek().equals(DayOfWeek.WEDNESDAY)){
                wednesday++;
            }
            if(r.getReservationStart().getDayOfWeek().equals(DayOfWeek.THURSDAY)){
                thursday++;
            }
            if(r.getReservationStart().getDayOfWeek().equals(DayOfWeek.FRIDAY)){
                friday++;
            }
            if(r.getReservationStart().getDayOfWeek().equals(DayOfWeek.SATURDAY)){
                saturday++;
            }
            if(r.getReservationStart().getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                sunday++;
            }
        }
        result.add(monday);
        result.add(tuesday);
        result.add(wednesday);
        result.add(thursday);
        result.add(friday);
        result.add(saturday);
        result.add(sunday);
        return result;
    }
}
