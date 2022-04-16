package com.example.demo.dto;

import com.example.demo.model.Address;

public class RegistrationRequestDto {

    private String firstName;
    private String lastName;
    private String streetNumber;
    private String streetName;
    private String city;
    private String country;
    private String phoneNumber;
    private String email;
    private String password;
    private String repeatedPassword;
    private String typeOfRegistration;
    private String descriptionOfRegistration;

    public RegistrationRequestDto() {
    }

    public RegistrationRequestDto(String firstName, String lastName, String streetNumber, String streetName, String city, String country, String phoneNumber, String email, String password, String repeatedPassword, String typeOfRegistration, String descriptionOfRegistration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
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
