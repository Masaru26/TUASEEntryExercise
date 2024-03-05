package com.example.aseuecrudticket.controller;

import com.example.aseuecrudticket.DTO.EventDTO;
import com.example.aseuecrudticket.DTO.EventDateDTO;
import com.example.aseuecrudticket.DTO.TicketDTO;
import com.example.aseuecrudticket.entity.Event;
import com.example.aseuecrudticket.entity.EventDate;
import com.example.aseuecrudticket.entity.Ticket;
import com.example.aseuecrudticket.repository.EventDateRepository;
import com.example.aseuecrudticket.repository.EventRepository;
import com.example.aseuecrudticket.repository.TicketRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("event")
public class EventController {

    private final EventRepository eventRepository;
    private final EventDateRepository eventDateRepository;
    private final TicketRepository ticketRepository;

    public EventController(EventRepository eventRepository, EventDateRepository eventDateRepository, TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.eventDateRepository = eventDateRepository;
        this.ticketRepository = ticketRepository;
    }

    @PostMapping("/new")
    public Event newEvent(@RequestBody EventDTO eventDTO) {
        Event e = new Event();
        e.setName(eventDTO.name());
        e.setMinAge(eventDTO.minAge());
        e.setOrganizer(eventDTO.organizer());

        return eventRepository.saveAndFlush(e);
    }

    @PostMapping("/{event_id}/addDate")
    public EventDate newEventDate(@PathVariable("event_id") Integer event_id, @RequestBody EventDateDTO eventDateDTO) {
        Event e = eventRepository.findById(event_id).get();
        EventDate ed = new EventDate();
        ed.setDateTime(eventDateDTO.dateTime());
        ed.setLocation(eventDateDTO.location());

        //e.getEventDates().add(ed);
        ed.setEvent(e);

        return eventDateRepository.saveAndFlush(ed);
    }

    @PostMapping("/{event_id}/addTicket")
    public Ticket newEventTicket(@PathVariable("event_id") Integer event_id, @RequestBody TicketDTO ticketDTO) {
        EventDate ed = eventDateRepository.findById(ticketDTO.eventDate_id()).get();
        Ticket t = new Ticket();
        t.setSeat(ticketDTO.seat());
        t.setPrice(ticketDTO.price());
        t.setDate(ed);

        return ticketRepository.saveAndFlush(t);
    }
}

