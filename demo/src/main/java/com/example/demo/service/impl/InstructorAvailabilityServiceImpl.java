package com.example.demo.service.impl;

import com.example.demo.model.users.InstructorAvailability;
import com.example.demo.repository.InstructorAvailabilityRepository;
import com.example.demo.service.InstructorAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Index;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        InstructorAvailability newAvailability = instructorAvailabilityRepository.save(instructorAvailability);
        List<InstructorAvailability> availabilities = getAllForInstructor(instructorAvailability.getInstructor().getId());
        List<InstructorAvailability> newAvailabilities = checkForOverlappingAvailabilities(availabilities, newAvailability);
        instructorAvailabilityRepository.saveAll(newAvailabilities);
        return newAvailability;
    }

    private List<InstructorAvailability> checkForOverlappingAvailabilities(List<InstructorAvailability> availabilityPeriods, InstructorAvailability newAvailability) {
        List<InstructorAvailability> newAvailabilities = new ArrayList<>();
        //List<InstructorAvailability> toRemove = new ArrayList<>();
        for (InstructorAvailability availability : availabilityPeriods) {
            if (!availability.getId().equals(newAvailability.getId()) &&
                    (isBetweenAvailabilityDate(newAvailability.getStartDate(), availability)
                    || isBetweenAvailabilityDate(newAvailability.getEndDate(), availability))) {
                LocalDateTime startDate = availability.getStartDate();
                LocalDateTime endDate = availability.getEndDate();
                instructorAvailabilityRepository.delete(availability);
                availability = calculateNewAvailability(startDate, endDate, newAvailability);
            } else if (newAvailability.getStartDate().isBefore(availability.getStartDate())
                    && newAvailability.getEndDate().isAfter(availability.getEndDate())) {
                availability.setStartDate(newAvailability.getStartDate());
                availability.setEndDate(newAvailability.getEndDate());
            }
            newAvailabilities.add(availability);
        }

        /*List<InstructorAvailability> newOne = newAvailabilities;
        for (InstructorAvailability remove : toRemove) {
            for (InstructorAvailability saved: newAvailabilities) {
                if(remove.getId().equals(saved.getId())){
                    newOne.remove(newAvailabilities.indexOf(saved));
                }
            }
        }*/
        return newAvailabilities;
    }

    private boolean isBetweenAvailabilityDate(LocalDateTime newDate, InstructorAvailability availability) {
        return (newDate.isBefore(availability.getEndDate()) && newDate.isAfter(availability.getStartDate()))
                || newDate.equals(availability.getEndDate()) || newDate.equals(availability.getStartDate());
    }

    private InstructorAvailability calculateNewAvailability(LocalDateTime startDate, LocalDateTime endDate, InstructorAvailability newAvailability) {
        if (startDate.isBefore(newAvailability.getStartDate()))
            newAvailability.setStartDate(startDate);

        if (endDate.isAfter(newAvailability.getEndDate()))
            newAvailability.setEndDate(endDate);

        return newAvailability;
    }


}
