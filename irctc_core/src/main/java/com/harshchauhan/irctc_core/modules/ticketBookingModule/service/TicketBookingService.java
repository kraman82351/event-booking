package com.harshchauhan.irctc_core.modules.ticketBookingModule.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.harshchauhan.irctc_core.entities.user.UserModel;

public class TicketBookingService {

    private final UserModel user;

    @Autowired
    public TicketBookingService(UserModel user) {
        this.user = user;
    }

}
