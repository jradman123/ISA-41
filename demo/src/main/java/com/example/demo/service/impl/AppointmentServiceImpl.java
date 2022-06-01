package com.example.demo.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentServiceImpl {

    @Autowired
    private AppointmentServiceImpl appointmentService;





}
