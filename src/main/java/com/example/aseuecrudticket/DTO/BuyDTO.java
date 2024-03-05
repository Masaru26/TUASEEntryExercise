package com.example.aseuecrudticket.DTO;

import java.util.Set;

public record BuyDTO(Integer person_id, Set<Integer> ticket_ids) {
}
