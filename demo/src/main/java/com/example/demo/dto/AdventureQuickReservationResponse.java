package com.example.demo.dto;

import java.util.List;
import java.util.Set;

public class AdventureQuickReservationResponse {

    private String id;
    private String startTime;
    private String endTime;
    private String validUntil;
    private String price;
    private String adventureId;
    private String guestLimit;
    private Set<ResponseUtility> utilities;
    private boolean isReserved;

    public AdventureQuickReservationResponse() {
    }

    public AdventureQuickReservationResponse(String id, String startTime, String endTime, String validUntil, String price, String adventureId, String guestLimit, Set<ResponseUtility> utilities, boolean isReserved) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validUntil = validUntil;
        this.price = price;
        this.adventureId = adventureId;
        this.guestLimit = guestLimit;
        this.utilities = utilities;
        this.isReserved = isReserved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Set<ResponseUtility> getUtilities() {
        return utilities;
    }

    public void setUtilities(Set<ResponseUtility> utilities) {
        this.utilities = utilities;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}


