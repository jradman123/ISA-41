package com.example.demo.dto;

public class AdministratorRegistrationDto {

        private String firstName;
        private String lastName;
        private String streetNumber;
        private String streetName;
        private String city;
        private String country;
        private String phoneNumber;
        private String email;
        private String password;

        public AdministratorRegistrationDto() {
        }

        public AdministratorRegistrationDto(String firstName, String lastName, String streetNumber, String streetName, String city, String country, String phoneNumber, String email, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.streetNumber = streetNumber;
            this.streetName = streetName;
            this.city = city;
            this.country = country;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.password = password;
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


    }


