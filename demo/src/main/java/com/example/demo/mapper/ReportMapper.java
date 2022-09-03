package com.example.demo.mapper;

import com.example.demo.dto.ReportResponse;
import com.example.demo.model.Report;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ReportMapper {

    public ReportResponse mapToResponse(Report report){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ReportResponse response = new ReportResponse();
        response.setId(report.getId().toString());
        response.setComment(report.getComment());
        if(report.isAppeared()) {
            response.setAppeared("Yes");
        }else{
            response.setAppeared("No");
        }
        if(report.isSanctioned()) {
            response.setSanctioned("Yes");
        }else{
            response.setSanctioned("No");
        }
        response.setDate(report.getDate().format(formatter));
        return response;
    }
}
