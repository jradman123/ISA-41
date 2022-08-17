package com.example.demo.service.impl;


import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.CreateAppointmentDto;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.reservation.Appointment;
import com.example.demo.model.users.User;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Autowired
    private CottageService cottageService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private EmailSenderServiceImpl emailSenderService;

    @Autowired
    private UserServiceImpl userService;



    @Override
    public List<AppointmentDto> findApp(Long id) {
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment appointment : appointmentRepository.findAll()) {
            if (appointment.getCottage() != null & appointment.isDeleted() == false) {
                if (id.equals(appointment.getCottage().getId())) {
                    appointmentDtos.add(new AppointmentDto(appointment));
                } else if (id.equals(appointment.getShip().getId())) {
                    appointmentDtos.add(new AppointmentDto(appointment));
                }
            }
        }
        return appointmentDtos;
    }

    @Override
    public ResponseEntity<Long> deleteApp(Long id) {
        List<Appointment> appointments=this.appointmentRepository.findAll();
        for (Appointment appointment: appointments)
        {
            if(appointment.getId()==id) {
                appointment.setDeleted(true);
                appointmentRepository.save(appointment);
            }

        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Override
    public List<AppointmentDto> search(AppointmentDto dto) {
        return null;
    }

    @Override
    public Appointment createAppointment(CreateAppointmentDto dto) {
       Appointment appointment=new Appointment(dto);
       if(dto.getCottageId()!=null) {
           Cottage cottage=cottageService.findCottageById(Long.parseLong(dto.getCottageId()));
           appointment.setCottage(cottage);
            appointmentRepository.save(appointment);

            for (User user: userService.findAll()) {
                notifyUser(user.getEmail(), cottage.getName(),appointment.getValidUntil());
            }
            return appointment;
       }else if(dto.getShipId()!=null) {

       }

       return null;
    }


    @Override
    public void notifyUser(String email, String name, LocalDateTime date) {
        emailSenderService.sendEmail(email,"Obavijest o novim terminima za rezervaciju","Imate nove termine za vikendicu   "   +name + "." + "Akcija vazi do  " + date + "."+
                "Mozete se prijaviti na nas sajt kako biste vidjeli detalje novog termina!");
    }




}
