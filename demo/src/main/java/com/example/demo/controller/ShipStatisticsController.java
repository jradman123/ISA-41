package com.example.demo.controller;

import com.example.demo.service.impl.ShipStatisticsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ship-statistics")
public class ShipStatisticsController {

    @Autowired
    private ShipStatisticsServiceImpl shipStatisticsService;

    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @GetMapping(value = "/per-days/{id}")
    public ResponseEntity<Map<String,Integer>> getStatisticPerDaysForShip(@PathVariable Long id) {
        return new ResponseEntity<>(shipStatisticsService.numberOfReservationPerDaysInWeekForShip(id), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @GetMapping(value = "/per-months/{id}")
    public ResponseEntity<Map<String,Integer>> getStatisticPerMonthsForShip(@PathVariable Long id) {
        return new ResponseEntity<>(shipStatisticsService.numberOfReservationPerMonthForShip(id), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @GetMapping(value = "/per-years/{id}")
    public ResponseEntity<Map<String,Integer>> getStatisticPerYearsForShip(@PathVariable Long id) {
        return new ResponseEntity<>(shipStatisticsService.numberOfReservationPerYearsForShip(id), HttpStatus.OK);

    }
}
