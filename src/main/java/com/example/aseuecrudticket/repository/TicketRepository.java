package com.example.aseuecrudticket.repository;

import com.example.aseuecrudticket.entity.Receipt;
import com.example.aseuecrudticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
