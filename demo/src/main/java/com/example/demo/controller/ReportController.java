package com.example.demo.controller;

import com.example.demo.dto.ReportDto;
import com.example.demo.dto.RoomDto;
import com.example.demo.model.Report;
import com.example.demo.model.cottages.Room;
import com.example.demo.service.ReportService;
import com.example.demo.service.impl.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {




    @Autowired
    private ReportServiceImpl reportService;


    @CrossOrigin(origins = "http://localhost:4200")

    @PostMapping(value = "/createReport")
    public Report createReport(@RequestBody ReportDto newReport) {

        return this.reportService.createReport(newReport);
    }




}
