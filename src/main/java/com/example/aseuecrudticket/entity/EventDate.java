package com.example.aseuecrudticket.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.Set;

@Entity
public class EventDate {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer eventDate_id;

    private Date dateTime;

    private String location;

    @OneToMany
    private Set<Ticket> availableTickets;

    @ManyToOne //(cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Event event;

    public Integer getEventDate_id() {
        return eventDate_id;
    }

    public void setEventDate_id(Integer eventDate_id) {
        this.eventDate_id = eventDate_id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Ticket> getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(Set<Ticket> availableTickets) {
        this.availableTickets = availableTickets;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
