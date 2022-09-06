package com.example.demo.service.impl;

import com.example.demo.dto.DeleteAccountRequest;
import com.example.demo.dto.RequestForDeletingAccountDto;
import com.example.demo.model.users.RegistrationRequest;
import com.example.demo.model.users.RequestForDeletingAccount;
import com.example.demo.model.users.User;
import com.example.demo.repository.RequestForDeletingAccountRepository;
import com.example.demo.service.RequestForDeletingAccountService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestForDeletingAccountServiceImpl implements RequestForDeletingAccountService {

    @Autowired
    private RequestForDeletingAccountRepository requestForDeletingAccountRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailSenderServiceImpl emailSenderService;

    @Override
    public List<RequestForDeletingAccountDto> findAll() {
        List<RequestForDeletingAccount> requests = requestForDeletingAccountRepository.findAll();
        List<RequestForDeletingAccountDto> requestsView = new ArrayList<>();
        for(RequestForDeletingAccount request : requests){
            User user = userService.findByEmail(request.getEmail());
            requestsView.add(new RequestForDeletingAccountDto(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhoneNumber(),
                                user.getUserType().toString(),request.getReason()));
        }
        return requestsView;
    }

    @Transactional
    @Override
    public void deleteAccount(String email, String response) {
        RequestForDeletingAccount request = requestForDeletingAccountRepository.findRequestByEmail(email);
        User user = userService.findByEmail(email);
        userService.deleteUser(user);
        deleteRequest(request);
        emailSenderService.sendEmail(email,"Brisanje naloga","Vaš zahtjev za brisanjem naloga je prihvaćen.\n" +
                "Odgovor admina na zahtjev: " + response);
    }
    @Transactional
    @Override
    public void rejectDeleting(String email, String response) {
        RequestForDeletingAccount request = requestForDeletingAccountRepository.findRequestByEmail(email);
        deleteRequest(request);
        emailSenderService.sendEmail(email,"Brisanje naloga","Vaš zahtjev za brisanjem naloga je odbijen.\n" +
                "Odgovor admina na zahtjev: " + response);
    }

    @Override
    public void deleteRequest(RequestForDeletingAccount request) {
        requestForDeletingAccountRepository.deleteById(request.getId());
    }

    @Override
    public RequestForDeletingAccount saveRequest(DeleteAccountRequest deleteAccountRequest) {
        RequestForDeletingAccount existRequest = requestForDeletingAccountRepository.findRequestByEmail(deleteAccountRequest.getEmail());
        if(existRequest != null){
            requestForDeletingAccountRepository.delete(existRequest);
        }
        RequestForDeletingAccount request = new RequestForDeletingAccount();
        request.setReason(deleteAccountRequest.getReason());
        request.setEmail(deleteAccountRequest.getEmail());
        return requestForDeletingAccountRepository.save(request);
    }
}
