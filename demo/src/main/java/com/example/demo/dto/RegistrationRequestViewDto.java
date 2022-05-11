package com.example.demo.dto;

public class RegistrationRequestViewDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String streetNumber;
    private String streetName;
    private String city;
    private String country;
    private String typeOfRegistration;
    private String descriptionOfRegistration;

    public RegistrationRequestViewDto() {
    }

    public RegistrationRequestViewDto(String firstName, String lastName, String email, String phoneNumber, String streetNumber, String streetName, String city, String country, String typeOfRegistration, String descriptionOfRegistration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.country = country;
        this.typeOfRegistration = typeOfRegistration;
        this.descriptionOfRegistration = descriptionOfRegistration;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
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

    public String getTypeOfRegistration() {
        return typeOfRegistration;
    }

    public void setTypeOfRegistration(String typeOfRegistration) {
        this.typeOfRegistration = typeOfRegistration;
    }

    public String getDescriptionOfRegistration() {
        return descriptionOfRegistration;
    }

    public void setDescriptionOfRegistration(String descriptionOfRegistration) {
        this.descriptionOfRegistration = descriptionOfRegistration;
    }
}
