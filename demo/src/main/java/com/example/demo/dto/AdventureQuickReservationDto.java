package com.example.demo.dto;

import java.util.List;

public class AdventureQuickReservationDto {

    private String startTime;
    private String endTime;
    private String validUntil;
    private String price;
    private String adventureId;
    private String guestLimit;
    private List<ResponseUtility> utilities;

    public AdventureQuickReservationDto() {
    }

    public AdventureQuickReservationDto(String startTime, String endTime, String validUntil, String price, String adventureId, String guestLimit, List<ResponseUtility> utilities) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.validUntil = validUntil;
        this.price = price;
        this.adventureId = adventureId;
        this.guestLimit = guestLimit;
        this.utilities = utilities;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAdventureId() {
        return adventureId;
    }

    public void setAdventureId(String adventureId) {
        this.adventureId = adventureId;
    }

    public String getGuestLimit() {
        return guestLimit;
    }

    public void setGuestLimit(String guestLimit) {
        this.guestLimit = guestLimit;
    }

    public List<ResponseUtility> getUtilities() {
        return utilities;
    }

    public void setUtilities(List<ResponseUtility> utilities) {
        this.utilities = utilities;
    }
}
