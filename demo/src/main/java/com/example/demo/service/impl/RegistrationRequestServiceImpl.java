package com.example.demo.service.impl;

import com.example.demo.dto.RegistrationRequestViewDto;
import com.example.demo.model.enumeration.RegistrationRequestStatus;
import com.example.demo.model.users.RegistrationRequest;
import com.example.demo.model.users.User;
import com.example.demo.repository.RegistrationRequestRepository;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.RegistrationRequestService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationRequestServiceImpl implements RegistrationRequestService {

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public List<RegistrationRequest> findAll() {
        return registrationRequestRepository.findAll();
    }

    @Override
    public List<RegistrationRequestViewDto> findAllAllowed() {
        List<RegistrationRequest> requests = registrationRequestRepository.findAllAllowed();
        List<RegistrationRequestViewDto> requestsView = new ArrayList<>();
        for(RegistrationRequest request : requests){
            User user = userService.findByEmail(request.getEmail());
            requestsView.add(new RegistrationRequestViewDto(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhoneNumber(),
                                user.getAddress().getStreetNumber(),user.getAddress().getStreetName(),user.getAddress().getCity(),
                                user.getAddress().getCountry(),user.getUserType().toString(),user.getDescriptionOfRegistration()));
        }
        return requestsView;
    }

    @Override
    public List<RegistrationRequestViewDto> findAllNotAllowed() {
        List<RegistrationRequest> requests = registrationRequestRepository.findAllNotAllowed();
        List<RegistrationRequestViewDto> requestsView = new ArrayList<>();
        for(RegistrationRequest request : requests){
            User user = userService.findByEmail(request.getEmail());
            requestsView.add(new RegistrationRequestViewDto(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhoneNumber(),
                    user.getAddress().getStreetNumber(),user.getAddress().getStreetName(),user.getAddress().getCity(),
                    user.getAddress().getCountry(),user.getUserType().toString(),user.getDescriptionOfRegistration()));
        }
        return requestsView;
    }

    @Override
    public void approveRequest(String email) {
        RegistrationRequest request = registrationRequestRepository.findByEmail(email);
        request.setRequestStatus(RegistrationRequestStatus.Approved);
        registrationRequestRepository.save(request);
        userService.activateAccount(email);
        emailSenderService.sendEmail(email,"Aktivacija naloga","Vaš zahtjev za registraciju je prihvaćen." +
                "Sada se možete prijaviti na naš sajt sa vašim kredencijalima.");
    }

    @Override
    public void rejectRequest(String email) {
        RegistrationRequest request = registrationRequestRepository.findByEmail(email);
        request.setRequestStatus(RegistrationRequestStatus.Rejected);
        registrationRequestRepository.save(request);
        emailSenderService.sendEmail(email,"Aktivacija naloga","Vaš zahtjev za registraciju je odbijen.");
    }

    @Override
    public List<RegistrationRequestViewDto> findAllPending() {
        List<RegistrationRequest> requests = registrationRequestRepository.findAllPending();
        List<RegistrationRequestViewDto> requestsView = new ArrayList<>();
        for(RegistrationRequest request : requests){
            User user = userService.findByEmail(request.getEmail());
            requestsView.add(new RegistrationRequestViewDto(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhoneNumber(),
                    user.getAddress().getStreetNumber(),user.getAddress().getStreetName(),user.getAddress().getCity(),
                    user.getAddress().getCountry(),user.getUserType().toString(),user.getDescriptionOfRegistration()));
        }
        return requestsView;
    }
}
