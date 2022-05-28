package com.example.demo.repository;

import com.example.demo.model.Rules;
import com.example.demo.model.cottages.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository  extends JpaRepository<Room, Long> {
}
