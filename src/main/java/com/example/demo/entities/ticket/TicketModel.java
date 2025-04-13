package com.example.demo.entities.ticket;

import jakarta.persistence.Entity;

@Entity
public class TicketModel {
    private String ticketId;
    private String userId;
    private String sourceStation;
    private String destinationStation;
    private Date dateOfJourney;

}
