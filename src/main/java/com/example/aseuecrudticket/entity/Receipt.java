package com.example.aseuecrudticket.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Receipt {

    public static final Float TAX = 20f;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer receipt_id;

    private Float totalPrice;
    private Float tax;
    private Float priceWOTax;

    @ManyToOne
    @JsonBackReference
    private Person buyer;

    @OneToMany
    private List<Ticket> tickets;

    public Integer getReceipt_id() {
        return receipt_id;
    }

    public void setReceipt_id(Integer receipt_id) {
        this.receipt_id = receipt_id;
    }

    public Float getTotalPrice() {
        Float price = 0f;
        for (Ticket t: this.getTickets()) {
            price += t.getPrice();
        }
        return price;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax() {
        this.tax = this.getTotalPrice() * TAX / 100;
    }

    public Float getPriceWOTax() {
        return priceWOTax;
    }

    public void setPriceWOTax() {
        this.priceWOTax = this.getTotalPrice() * (100-TAX)/100;
    }

    public Person getBuyer() {
        return buyer;
    }

    public void setBuyer(Person buyer) {
        this.buyer = buyer;
    }

    public List<Ticket> getTickets() {
        if (tickets == null) {
            tickets = new ArrayList<Ticket>();
        }
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
