package com.example.demo.model.users;

import com.example.demo.model.enumeration.RegistrationRequestStatus;

import javax.persistence.*;

@Entity
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private RegistrationRequestStatus requestStatus;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String email) {
        this.email = email;
        this.requestStatus = RegistrationRequestStatus.Pending;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegistrationRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RegistrationRequestStatus status) {
        requestStatus = status;
    }
}
