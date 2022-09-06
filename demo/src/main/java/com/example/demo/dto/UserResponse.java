package com.example.demo.dto;
import com.example.demo.model.users.User;


public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String city;
    private String country;
    private String streetNumber;
    private String street;
    private String type;
    private String points;

    public UserResponse() {
    }

    public UserResponse(String id, String name, String email, String phoneNumber, String city, String country, String streetNumber, String street, String type, String points) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.country = country;
        this.streetNumber = streetNumber;
        this.street = street;
        this.type = type;
        this.points = points;
    }

    public  UserResponse(User user){
        id=user.getId().toString();
        name = user.getFullName();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
        city = user.getAddress().getCity();
        country = user.getAddress().getCountry();
        streetNumber = user.getAddress().getStreetNumber();
        street = user.getAddress().getStreetName();
        type = user.getUserType().name();
        points = String.valueOf(user.getScoredPoints());
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
