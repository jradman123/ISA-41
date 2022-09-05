package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.mapper.ReportMapper;
import com.example.demo.model.Report;
import com.example.demo.model.cottages.Room;
import com.example.demo.service.ReportService;
import com.example.demo.service.impl.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;

    @Autowired
    private ReportMapper reportMapper;


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('CottageAdvertiser') || hasAuthority('ShipAdvertiser') || hasAuthority('Instructor')")
    @PostMapping(value = "/createReport")
    public Report createReport(@RequestBody ReportDto newReport) {

        return this.reportService.createReport(newReport);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('CottageAdvertiser') || hasAuthority('ShipAdvertiser') || hasAuthority('Instructor') || hasAuthority('Admin')")
    @GetMapping(value = "/findReportByResId/{id}")
    public ReportDto findReportbyResId(@PathVariable Long id) {

        Report report= this.reportService.findReportbyResId(id);
        ReportDto reportDto=new ReportDto(report);

        return reportDto;
    }



    @GetMapping("/haveReport/{id}")
    public ResponseEntity<HaveReportDto> haveReport(@PathVariable Long id){

        HaveReportDto haveReportDto = new HaveReportDto();
        Report report=reportService.findReportbyResId(id);
        if(report!=null){
            haveReportDto.setMessage("TRUE");
        }else {
            haveReportDto.setMessage("FALSE");
        }
        return new ResponseEntity<HaveReportDto>(haveReportDto, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/unseen")
    public ResponseEntity<List<ReportResponse>> getAllReports(){
        List<ReportResponse> response = new ArrayList<>();
        for (Report report: reportService.getAllUnseen()) {
            response.add(reportMapper.mapToResponse(report));
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/approve/{id}")
    public ResponseEntity<List<ReportResponse>> approveReport(@PathVariable Long id){
        List<ReportResponse> response = new ArrayList<>();
        for (Report report: reportService.approveReport(id)) {
            response.add(reportMapper.mapToResponse(report));
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/reject/{id}")
    public ResponseEntity<List<ReportResponse>> rejectReport(@PathVariable Long id){
        List<ReportResponse> response = new ArrayList<>();
        for (Report report: reportService.rejectReport(id)) {
            response.add(reportMapper.mapToResponse(report));
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
