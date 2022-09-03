package com.example.demo.dto;

import java.time.LocalDateTime;

public class DataForSendEmail {

    private String clientEmail;
    private String ownerEmail;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String fullName;

    public DataForSendEmail() {
    }

    public DataForSendEmail(String clientEmail, String ownerEmail, LocalDateTime startTime, LocalDateTime endTime, String fullName) {
        this.clientEmail = clientEmail;
        this.ownerEmail = ownerEmail;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fullName = fullName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
