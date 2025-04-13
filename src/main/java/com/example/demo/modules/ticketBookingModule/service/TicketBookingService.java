package com.example.demo.modules.ticketBookingModule.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.user.UserModel;

public class TicketBookingService {

    private final UserModel user;

    @Autowired
    public TicketBookingService(UserModel user) {
        this.user = user;
    }

}
