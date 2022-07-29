package com.example.demo.dto;

import lombok.Data;

public class DeleteAccountRequest {

    private String email;
    private String reason;

    public DeleteAccountRequest() {
    }

    public DeleteAccountRequest(String email, String reason) {
        this.email = email;
        this.reason = reason;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
