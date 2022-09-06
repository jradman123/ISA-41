package com.example.demo.repository;

import com.example.demo.model.users.RegistrationRequest;
import com.example.demo.model.users.RequestForDeletingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

@Repository
public interface RequestForDeletingAccountRepository extends JpaRepository<RequestForDeletingAccount, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query(value = "select r from RequestForDeletingAccount r where r.email = :email")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    RequestForDeletingAccount findRequestByEmail(@Param("email") String email);
}
