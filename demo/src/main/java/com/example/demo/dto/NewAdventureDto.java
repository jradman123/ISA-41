package com.example.demo.dto;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewAdventureDto {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String streetName;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotBlank
    private String streetNumber;
    @NotBlank
    private String guestLimit;
    @NotBlank
    private String price;
    @NotBlank
    private String cancellationConditions;
    private Double longitude;
    private Double latitude;

    public NewAdventureDto() {
    }

    public NewAdventureDto(String name, String description, String streetName, String city, String country, String streetNumber, String guestLimit, String price, String cancellationConditions,Double longitude,Double latitude) {
        this.name = name;
        this.description = description;
        this.streetName = streetName;
        this.city = city;
        this.country = country;
        this.streetNumber = streetNumber;
        this.guestLimit = guestLimit;
        this.price = price;
        this.cancellationConditions = cancellationConditions;
        this.latitude=latitude;
        this.longitude=longitude;
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

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
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

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
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

    public String getCancellationConditions() {
        return cancellationConditions;
    }

    public void setCancellationConditions(String cancellationConditions) {
        this.cancellationConditions = cancellationConditions;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
