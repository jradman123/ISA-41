package com.example.demo.repository;

import com.example.demo.model.users.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query(value = "select r from RegistrationRequest r where r.email = :email")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    RegistrationRequest findRequestByEmail(@Param("email") String email);

}
