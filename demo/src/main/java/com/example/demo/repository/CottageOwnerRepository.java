package com.example.demo.repository;

import com.example.demo.model.users.CottageOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CottageOwnerRepository extends JpaRepository<CottageOwner, Long> {

}
