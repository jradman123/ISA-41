package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;

public interface EmailSenderService {

    void sendEmail(String toEmail,String subject,String body);
}
