package com.example.demo.controller;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.service.impl.AppointmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentService;


    @GetMapping(value="/getAppByCottage/{id}")
    public List<AppointmentDto> getAllByCottage(@PathVariable Long id) {
       return this.appointmentService.findAppByCottage(id);
    }
}
