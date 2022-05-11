package com.example.demo.repository;

import com.example.demo.model.users.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
    RegistrationRequest findByEmail(String email);

    @Query(value = "select * from registration_request r where r.request_status = 1", nativeQuery = true)
    List<RegistrationRequest> findAllAllowed();

    @Query(value = "select * from registration_request r where r.request_status = 2", nativeQuery = true)
    List<RegistrationRequest> findAllNotAllowed();

    @Query(value = "select * from registration_request r where r.request_status = 0", nativeQuery = true)
    List<RegistrationRequest> findAllPending();
}
