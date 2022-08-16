package com.example.demo.mapper;

import com.example.demo.dto.InstructorAvailabilityDto;
import com.example.demo.dto.NewInstructorAvailability;
import com.example.demo.model.users.Instructor;
import com.example.demo.model.users.InstructorAvailability;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InstructorAvailabilityMapper {


    public InstructorAvailabilityDto mapToInstructorAvailabilityDto(InstructorAvailability instructorAvailability){
        InstructorAvailabilityDto instructorAvailabilityDto = new InstructorAvailabilityDto();
        instructorAvailabilityDto.setId(instructorAvailability.getId().toString());
        instructorAvailabilityDto.setStartDate(instructorAvailability.getStartDate().toString());
        instructorAvailabilityDto.setEndDate(instructorAvailability.getEndDate().toString());
        return instructorAvailabilityDto;
    }

    public InstructorAvailability mapNewInstructorAvailabilityToEntity(NewInstructorAvailability newInstructorAvailability,
                                                                       Instructor instructor){
        InstructorAvailability instructorAvailability = new InstructorAvailability();
        instructorAvailability.setStartDate(LocalDateTime.parse(newInstructorAvailability.getStartDate()));
        instructorAvailability.setEndDate(LocalDateTime.parse(newInstructorAvailability.getEndDate()));
        instructorAvailability.setInstructor(instructor);
        return instructorAvailability;
    }
}
