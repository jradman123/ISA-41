package com.example.demo.service.impl;

import com.example.demo.model.users.RegisteredUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.RegistrationForClientsService;
import com.example.demo.service.UserService;
import org.springframework.core.env.Environment;

import java.net.UnknownHostException;

public class RegistrationForClientsServiceImpl implements RegistrationForClientsService {

    private UserService userService;
    private UserRepository userRepository;
    private EmailSenderService emailService;
    private Environment environment;
    private String confirmationControllerPath="/api/auth/verify-client/";
    private String emailText = "You're almost ready to start enjoying Adventure!\n\n" +
                                "Simply click the link below to verify your email address.";

    @Override
    public RegisteredUser sendVerificationEmail(RegisteredUser user) throws UnknownHostException {
        int port = Integer.parseInt(environment.getProperty("server.port"));
        userRepository.save(user);
        emailService.sendEmail(user.getEmail(),"You're almost done!","Hello " + user.getFirstName() + ",\n"
                + emailText + "\n\n" + "http://localhost:"+port+confirmationControllerPath+user.getEmail() +
                "\n\nBest regards, the Adventure team");
        //System.out.println(emailText+"http://localhost:"+port+confirmationControllerPath+user.getEmail());
        return user;
    }

    @Override
    public RegisteredUser registerClient(RegisteredUser user) {
        user.setActivated(true);
        userRepository.save(user);
        return user;
    }
}
