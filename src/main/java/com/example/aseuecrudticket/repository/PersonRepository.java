package com.example.aseuecrudticket.repository;

import com.example.aseuecrudticket.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
