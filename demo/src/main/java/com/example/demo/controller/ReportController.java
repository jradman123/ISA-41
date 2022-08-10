package com.example.demo.controller;

import com.example.demo.dto.CottageDto;
import com.example.demo.dto.HaveReportDto;
import com.example.demo.dto.ReportDto;
import com.example.demo.dto.RoomDto;
import com.example.demo.model.Report;
import com.example.demo.model.cottages.Room;
import com.example.demo.service.ReportService;
import com.example.demo.service.impl.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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


    @CrossOrigin(origins = "http://localhost:4200")
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

}
