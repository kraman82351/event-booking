package com.harshchauhan.irctc_notification_email.kafka.dto;

public record TrainSeatBookingConfirmationDto(String trainId, Integer seatNumber, String userEmail) {
}
