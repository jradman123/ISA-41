package com.example.demo.model.users;

import javax.persistence.*;

@Entity
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private boolean isAllowed;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String email) {
        this.email = email;
        this.isAllowed = false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }
}
