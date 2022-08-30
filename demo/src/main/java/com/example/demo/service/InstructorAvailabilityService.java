package com.example.demo.service;

import com.example.demo.model.users.InstructorAvailability;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.List;

public interface InstructorAvailabilityService {

    List<InstructorAvailability> getAllForInstructor(int instructorId);
    List<InstructorAvailability> getAllCurrentAndFutureForInstructor(int instructorId);
    InstructorAvailability addNewAvailability(InstructorAvailability instructorAvailability);
    boolean isInstructorAvailable(int id, LocalDateTime startTime, LocalDateTime endTime);
}
