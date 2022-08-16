package com.example.demo.service.impl;

import com.example.demo.model.users.Instructor;
import com.example.demo.model.users.InstructorAvailability;
import com.example.demo.repository.InstructorAvailabilityRepository;
import com.example.demo.service.InstructorAvailabilityService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorAvailabilityServiceImpl implements InstructorAvailabilityService {

    @Autowired
    private InstructorAvailabilityRepository instructorAvailabilityRepository;

    @Override
    public List<InstructorAvailability> getAllForInstructor(int instructorId) {
        return instructorAvailabilityRepository.getAllForInstructor(instructorId);
    }

    @Override
    public InstructorAvailability addNewAvailability(InstructorAvailability instructorAvailability) {
        InstructorAvailability mergedPeriod = mergeNewInstructorAvailability(instructorAvailability, instructorAvailability.getInstructor().getId());
        return instructorAvailabilityRepository.save(mergedPeriod);
    }

    private InstructorAvailability mergeNewInstructorAvailability(InstructorAvailability newAvailability, int instructorId) {
        for (var period: instructorAvailabilityRepository.getAllForInstructor(instructorId)) {
            if(shouldBeMerge(newAvailability,period)) {
                instructorAvailabilityRepository.delete(period);
            }
        }
        return newAvailability;
    }

    public boolean shouldBeMerge(InstructorAvailability first,InstructorAvailability second) {
        if(first.getStartDate().equals(second.getEndDate())) {
            first.setStartDate(second.getStartDate());
            return true;
        }
        if(first.getEndDate().equals(second.getStartDate())) {
            first.setEndDate(second.getEndDate());
            return true;
        }
        return false;
    }
}
