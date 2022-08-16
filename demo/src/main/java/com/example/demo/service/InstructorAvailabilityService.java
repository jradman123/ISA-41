package com.example.demo.service;

import com.example.demo.model.users.InstructorAvailability;
import java.util.List;

public interface InstructorAvailabilityService {

    List<InstructorAvailability> getAllForInstructor(int instructorId);
    InstructorAvailability addNewAvailability(InstructorAvailability instructorAvailability);
}
