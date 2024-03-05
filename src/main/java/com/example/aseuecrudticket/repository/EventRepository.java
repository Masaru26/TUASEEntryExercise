package com.example.aseuecrudticket.repository;

import com.example.aseuecrudticket.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
