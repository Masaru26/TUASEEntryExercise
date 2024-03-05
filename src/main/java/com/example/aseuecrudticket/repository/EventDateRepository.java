package com.example.aseuecrudticket.repository;

import com.example.aseuecrudticket.entity.Event;
import com.example.aseuecrudticket.entity.EventDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDateRepository extends JpaRepository<EventDate, Integer> {
}
