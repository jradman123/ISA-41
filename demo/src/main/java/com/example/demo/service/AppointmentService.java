package com.example.demo.service;

import com.example.demo.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {

    List<AppointmentDto> findAppByCottage(Long id);
}
