package com.example.demo.dto;

import lombok.Getter;

@Getter
public class AdventureDto {

    private String id;
    private String name;
    private String description;
    private String guestLimit;
    private String price;
    private String streetName;
    private String streetNumber;
    private String city;
    private String country;
    private String cancellationConditions;
    private String instructorsBiography;
    private Double longitude;
    private Double latitude;

    public AdventureDto() {
    }

    public AdventureDto(String id, String name, String description, String guestLimit, String price, String streetName, String streetNumber, String city, String country, String cancellationConditions, String instructorsBiography,Double longitude,Double latitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.guestLimit = guestLimit;
        this.price = price;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.country = country;
        this.cancellationConditions = cancellationConditions;
        this.instructorsBiography = instructorsBiography;
        this.longitude=longitude;
        this.latitude=latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuestLimit() {
        return guestLimit;
    }

    public void setGuestLimit(String guestLimit) {
        this.guestLimit = guestLimit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCancellationConditions() {
        return cancellationConditions;
    }

    public void setCancellationConditions(String cancellationConditions) {
        this.cancellationConditions = cancellationConditions;
    }

    public String getInstructorsBiography() {
        return instructorsBiography;
    }

    public void setInstructorsBiography(String instructorsBiography) {
        this.instructorsBiography = instructorsBiography;
    }
}
