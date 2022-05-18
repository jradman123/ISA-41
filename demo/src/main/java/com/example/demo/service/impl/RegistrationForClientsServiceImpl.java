package com.example.demo.service.impl;

import com.example.demo.model.users.RegisteredUser;
import com.example.demo.model.users.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.RegistrationForClientsService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.Objects;

@Service
public class RegistrationForClientsServiceImpl implements RegistrationForClientsService {

    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSenderService emailService;
    private Environment environment;

    @Override
    public RegisteredUser sendVerificationEmail(RegisteredUser user) throws UnknownHostException {
        //int port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("server.port")));
        //userRepository.save(user);
        String confirmationControllerPath = "/auth/verify-client/";
        String emailText = "You're almost ready to start enjoying Adventure!\n\n" +
                "Simply click the link below to verify your email address.";
        emailService.sendEmail(user.getEmail(),"You're almost done!","Hello " + user.getFirstName() + ",\n"
                + emailText + "\n\n" + "http://localhost:8080"+ confirmationControllerPath +user.getEmail() +
                "\n\nBest regards, the Adventure team");
        //System.out.println(emailText+"http://localhost:"+port+confirmationControllerPath+user.getEmail());
        return user;
    }

    @Override
    public User registerClient(User user) {
        User dbUser = userRepository.findByEmail(user.getEmail());
        dbUser.setActivated(true);
        userRepository.save(dbUser);
        return dbUser;
    }
}
