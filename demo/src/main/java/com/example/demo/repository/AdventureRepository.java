package com.example.demo.repository;

import com.example.demo.model.adventures.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdventureRepository extends JpaRepository<Adventure, Integer> {

    @Query(value = "select * from adventure a where instructor = ?1 and deleted = false", nativeQuery = true)
    List<Adventure> getAllForInstructor(int instructorId);

    @Query(value = "select * from adventure a where deleted = false", nativeQuery = true)
    List<Adventure> getAllUndeleted();
}
