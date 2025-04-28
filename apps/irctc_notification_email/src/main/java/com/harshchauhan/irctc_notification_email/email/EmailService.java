package com.harshchauhan.irctc_notification_email.email;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.harshchauhan.irctc_notification_email.kafka.dto.TrainSeatBookingConfirmationDto;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine) {
        this.mailSender = javaMailSender;
        this.templateEngine = springTemplateEngine;
    }

    @Async
    public void sendBookingSuccessEmail(TrainSeatBookingConfirmationDto trainSeatBookingConfirmationDto)
            throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());

        mimeMessageHelper.setFrom("support@irctc.in");
        mimeMessageHelper.setSubject(EmailTemplates.TRAIN_SEAT_BOOKING_CONFIRMATION.getSubject());
        mimeMessageHelper.setTo(trainSeatBookingConfirmationDto.userEmail());

        Map<String, Object> variables = new HashMap<>();
        variables.put("userEmail", trainSeatBookingConfirmationDto.userEmail());
        variables.put("trainId", trainSeatBookingConfirmationDto.trainId());
        variables.put("seatNumber", trainSeatBookingConfirmationDto.seatNumber());

        Context context = new Context();
        context.setVariables(variables);

        try {
            String htmlTemplate = templateEngine.process(
                    EmailTemplates.TRAIN_SEAT_BOOKING_CONFIRMATION.getTemplate(),
                    context);

            mimeMessageHelper.setText(htmlTemplate, true);
            mailSender.send(mimeMessage);

            log.info("Email successfully send to :: {} :: with template :: {} ::",
                    trainSeatBookingConfirmationDto.userEmail(),
                    EmailTemplates.TRAIN_SEAT_BOOKING_CONFIRMATION.getTemplate());
        } catch (MessagingException e) {
            log.warn("Cannot set email to :: {} :: ", trainSeatBookingConfirmationDto.userEmail());
        }
    }
}
