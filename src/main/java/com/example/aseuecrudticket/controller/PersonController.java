package com.example.aseuecrudticket.controller;

import com.example.aseuecrudticket.DTO.BuyDTO;
import com.example.aseuecrudticket.DTO.PersonDTO;
import com.example.aseuecrudticket.entity.Person;
import com.example.aseuecrudticket.entity.Receipt;
import com.example.aseuecrudticket.entity.Ticket;
import com.example.aseuecrudticket.repository.PersonRepository;
import com.example.aseuecrudticket.repository.ReceiptRepository;
import com.example.aseuecrudticket.repository.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("person")
public class PersonController {

    private final PersonRepository personRepository;
    private final TicketRepository ticketRepository;
    private final ReceiptRepository receiptRepository;

    public PersonController(PersonRepository personRepository, TicketRepository ticketRepository, ReceiptRepository receiptRepository) {
        this.personRepository = personRepository;
        this.ticketRepository = ticketRepository;
        this.receiptRepository = receiptRepository;
    }

    @GetMapping("/all")
    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @PostMapping("/new")
    public Person newPerson(@RequestBody PersonDTO personDTO) {
        Person p = new Person();

        p.setName(personDTO.name());
        p.setBirthdate(personDTO.birthdate());
        return personRepository.saveAndFlush(p);
    }

    @GetMapping("/tickets")
    public Iterable<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("/receipts")
    public Iterable<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    @PostMapping("/tickets/buy")
    public Receipt buyTickets(@RequestBody BuyDTO buyDTO) {
        List<Ticket> ticketsToBuy = ticketRepository.findAllById(buyDTO.ticket_ids()).stream().toList();

        Person p = personRepository.findById(buyDTO.person_id()).get();

        LocalDate birthdate = p.getBirthdate();

        Receipt r = new Receipt();

        for (Ticket t: ticketsToBuy) {
            LocalDate minBirthdate = LocalDate.now().minusYears(t.getDate().getEvent().getMinAge());

            if (minBirthdate.isBefore(birthdate)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person is too young to buy ticket: " + t.getTicket_id());
            }

            if (t.getOwner() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket already owned: " + t.getTicket_id());
            }

            p.getTickets().add(t);
            r.getTickets().add(t);
            //t.setOwner(p);
        }

        r.setPriceWOTax();
        r.setTax();

        r.setBuyer(p);
        return receiptRepository.saveAndFlush(r);
    }
}
