package com.example.demo.service;

import com.example.demo.dto.ReportDto;
import com.example.demo.model.Report;
import com.example.demo.model.cottages.Room;

import java.util.List;

public interface ReportService {
    Report createReport(ReportDto newReport);

    Report findReportbyResId(Long id);
    List<Report> getAllUnseen();
    List<Report> approveReport(Long id);
    List<Report> rejectReport(Long id);
}
