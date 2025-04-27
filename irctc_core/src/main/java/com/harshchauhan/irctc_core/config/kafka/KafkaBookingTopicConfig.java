package com.harshchauhan.irctc_core.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaBookingTopicConfig {

    @Bean("bookingTopic")
    public NewTopic bookingTopic() {
        return TopicBuilder.name("booking-topic").build();
    }

}
