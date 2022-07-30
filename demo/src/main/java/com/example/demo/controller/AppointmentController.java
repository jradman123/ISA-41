package com.example.demo.controller;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.CottageDto;
import com.example.demo.dto.QuickReservationDto;
import com.example.demo.dto.ShipDto;
import com.example.demo.model.adventures.QuickReservationUtility;
import com.example.demo.model.reservation.Appointment;
import com.example.demo.service.impl.AppointmentServiceImpl;
import com.example.demo.service.impl.ShipServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentService;



    @GetMapping(value="/getApp/{id}")
    //idCottage or idBoat
    public List<AppointmentDto> getAllByCottage(@PathVariable Long id) {
       return this.appointmentService.findApp(id);
    }

    @DeleteMapping(value="/deleteApp/{id}")
    public ResponseEntity<Long> deleteApp(@PathVariable Long id) {
        return this.appointmentService.deleteApp(id);
    }

    @GetMapping(value = "/search")
    public List<AppointmentDto> search(AppointmentDto dto) {
        return this.appointmentService.search(dto);
    }


    @RequestMapping(value="/createApp", method = RequestMethod.POST)

    public Appointment createApp(@RequestBody AppointmentDto dto) {
        return this.appointmentService.createAppointment(dto);
    }

}
