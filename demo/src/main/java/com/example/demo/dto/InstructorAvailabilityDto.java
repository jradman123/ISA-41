package com.example.demo.dto;

public class InstructorAvailabilityDto {

    private String id;
    private String startDate;
    private String endDate;

    public InstructorAvailabilityDto() {
    }

    public InstructorAvailabilityDto(String id, String startDate, String endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
