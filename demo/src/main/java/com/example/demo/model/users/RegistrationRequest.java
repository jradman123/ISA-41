package com.example.demo.model.users;

import javax.persistence.*;

@Entity
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
