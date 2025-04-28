package com.harshchauhan.irctc_core.modules.trainSeatModule.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.harshchauhan.irctc_core.modules.trainSeatModule.dtos.TrainSeatBookingConfirmationDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainSeatBookingProducer {

    private final KafkaTemplate<String, TrainSeatBookingConfirmationDto> kafkaTemplate;

    public void sendTrainBookingConfirmation(TrainSeatBookingConfirmationDto trainSeatBookingConfirmation) {
        log.info("Sending train seat booking confirmation {}", trainSeatBookingConfirmation);
        Message<TrainSeatBookingConfirmationDto> message = MessageBuilder
                .withPayload(trainSeatBookingConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "booking-topic")
                .build();

        kafkaTemplate.send(message);
    }

}
