package com.example.demo.service;

import com.example.demo.dto.ReportDto;
import com.example.demo.model.Report;
import com.example.demo.model.cottages.Room;

public interface ReportService {
    Report createReport(ReportDto newReport);

    Report findReportbyResId(Long id);
}
