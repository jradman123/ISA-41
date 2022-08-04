package com.example.demo.dto;

import com.example.demo.model.users.User;

public class PersonalData {

    private String firstName;
    private String lastName;
    private String streetNumber;
    private String streetName;
    private String city;
    private String country;
    private String phoneNumber;
    private String email;

    public PersonalData() {
    }



    public PersonalData(String firstName, String lastName, String streetNumber, String streetName, String city, String country, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public PersonalData(User user) {
        firstName=user.getFirstName();
        lastName=user.getLastName();
        streetName=user.getAddress().getStreetName();
        streetNumber=user.getAddress().getStreetNumber();
        city=user.getAddress().getCity();
        country=user.getAddress().getCountry();
        phoneNumber=user.getPhoneNumber();
        email=user.getEmail();

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
}
