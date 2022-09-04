package com.example.demo.service;

import com.example.demo.dto.ComplaintDto;

import java.util.List;

public interface ComplaintService {

    List<ComplaintDto> getAllUnanswered();
}
