package com.example.demo.controller;


import com.example.demo.service.impl.CottageStatisticsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cottage-statistics")
public class CottageStatisticsController {

    @Autowired
    CottageStatisticsServiceImpl cottageStatisticService;

    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @GetMapping(value = "/per-days/{id}")
    public ResponseEntity<Map<String,Integer>> getStatisticPerDaysForCottage(@PathVariable Long id) {
        return new ResponseEntity<>(cottageStatisticService.numberOfReservationPerDaysInWeekForCottage(id), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @GetMapping(value = "/per-months/{id}")
    public ResponseEntity<Map<String,Integer>> getStatisticPerMonthsForCottage(@PathVariable Long id) {
        return new ResponseEntity<>(cottageStatisticService.numberOfReservationPerMonthForCottage(id), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @GetMapping(value = "/ship/per-years/{id}")
    public ResponseEntity<Map<String,Integer>> getStatisticPerYearsForCottage(@PathVariable Long id) {
        return new ResponseEntity<>(cottageStatisticService.numberOfReservationPerYearsForCottage(id), HttpStatus.OK);

    }





}
