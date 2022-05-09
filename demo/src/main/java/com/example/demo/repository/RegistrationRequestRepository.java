package com.example.demo.repository;

import com.example.demo.model.users.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
    RegistrationRequest findByEmail(String email);

    @Query(value = "select * from registration_request r where r.is_allowed = true", nativeQuery = true)
    List<RegistrationRequest> findAllAllowed();

    @Query(value = "select * from registration_request r where r.is_allowed = false", nativeQuery = true)
    List<RegistrationRequest> findAllNotAllowed();
}
