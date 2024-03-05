package com.example.aseuecrudticket.repository;

import com.example.aseuecrudticket.entity.EventDate;
import com.example.aseuecrudticket.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
}
