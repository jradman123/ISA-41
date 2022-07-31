package com.example.demo.repository;

import com.example.demo.model.Image;
import com.example.demo.model.cottages.CottageUtility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
