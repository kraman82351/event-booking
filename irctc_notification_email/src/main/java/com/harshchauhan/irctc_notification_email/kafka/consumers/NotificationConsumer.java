package com.harshchauhan.irctc_notification_email.kafka.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.harshchauhan.irctc_notification_email.email.EmailService;
import com.harshchauhan.irctc_notification_email.kafka.dto.TrainSeatBookingConfirmationDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "booking-topic")
    public void consumeTrainSeatBookingSuccessNotification(
            TrainSeatBookingConfirmationDto trainSeatBookingConfirmationDto) {

        log.info("Consuming message from booking success topic :: {}", trainSeatBookingConfirmationDto);

        try {
            emailService.sendBookingSuccessEmail(trainSeatBookingConfirmationDto);
        } catch (Exception e) {
            log.warn("Something went wrong when sending email");
        }
    }

}
