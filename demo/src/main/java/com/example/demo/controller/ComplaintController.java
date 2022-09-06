package com.example.demo.controller;

import com.example.demo.dto.Answer;
import com.example.demo.dto.ComplaintDto;
import com.example.demo.dto.ReportResponse;
import com.example.demo.model.Report;
import com.example.demo.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/unanswered")
    public ResponseEntity<List<ComplaintDto>> getAllUnanswered(){
        return new ResponseEntity<>(complaintService.getAllUnanswered(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/{id}/answer")
    public void answerOnComplaint(@PathVariable Long id,@RequestBody Answer answer)  {
        complaintService.answerOnComplaint(id,answer.getAnswer());
    }
}
