package com.example.demo.dto;

public class DetailsAboutReservation {

    private String id;
    private String resStart;
    private String resEnd;
    private Integer numberOfPerson;
    private Double price;
    private String clientEmail;
    private String clientName;
    private String nameOfObject;

    public DetailsAboutReservation() {
    }

    public DetailsAboutReservation(String id, String resStart, String resEnd, Integer numberOfPerson, Double price, String clientEmail, String clientName, String nameOfObject) {
        this.id = id;
        this.resStart = resStart;
        this.resEnd = resEnd;
        this.numberOfPerson = numberOfPerson;
        this.price = price;
        this.clientEmail = clientEmail;
        this.clientName = clientName;
        this.nameOfObject = nameOfObject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResStart() {
        return resStart;
    }

    public void setResStart(String resStart) {
        this.resStart = resStart;
    }

    public String getResEnd() {
        return resEnd;
    }

    public void setResEnd(String resEnd) {
        this.resEnd = resEnd;
    }

    public Integer getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(Integer numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getNameOfObject() {
        return nameOfObject;
    }

    public void setNameOfObject(String nameOfObject) {
        this.nameOfObject = nameOfObject;
    }

}
