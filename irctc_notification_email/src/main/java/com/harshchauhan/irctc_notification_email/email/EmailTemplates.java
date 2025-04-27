package com.harshchauhan.irctc_notification_email.email;

import lombok.Getter;

public enum EmailTemplates {
    TRAIN_SEAT_BOOKING_CONFIRMATION("train-seat-confirmation.html", "Train Seat Successfully Booked!");

    @Getter
    private final String template;

    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
