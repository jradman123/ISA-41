package com.example.demo.repository;

import com.example.demo.model.cottages.CottageImage;
import com.example.demo.model.users.CottageOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CottageImageRepository  extends JpaRepository<CottageImage, Long> {
}
