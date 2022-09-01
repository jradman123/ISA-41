package com.example.demo.controller;

import com.example.demo.security.TokenUtils;
import com.example.demo.service.StatisticService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('Instructor')")
    @GetMapping(value = "/statistic-per-days")
    public ResponseEntity<List<Integer>> getStatisticPerDaysForInstructor(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String email = tokenUtils.getEmailFromToken(token);
        return new ResponseEntity<>(statisticService.numberOfReservationPerDaysInWeekForInstructor(userService.findByEmail(email).getId()), HttpStatus.OK);

    }
}
