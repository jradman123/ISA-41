package com.example.demo.repository;

import com.example.demo.model.users.RegistrationRequest;
import com.example.demo.model.users.RequestForDeletingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface RequestForDeletingAccountRepository extends JpaRepository<RequestForDeletingAccount, Long> {
    RequestForDeletingAccount findByEmail(String email);
}
