package com.example.demo.dto;

public class AuthenticatedUserDto {
    private String email;
    private String role;
    private UserTokenState token;
    private boolean firstLogin;
    private boolean predefAdmin;

    public boolean isPredefAdmin() {
        return predefAdmin;
    }

    public void setPredefAdmin(boolean predefAdmin) {
        this.predefAdmin = predefAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserTokenState getToken() {
        return token;
    }

    public void setToken(UserTokenState token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public AuthenticatedUserDto() {
    }

    public AuthenticatedUserDto(String email,String role, UserTokenState token) {
        this.email = email;
        this.role = role;
        this.token = token;
    }
}
