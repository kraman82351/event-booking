package com.harshchauhan.irctc_core.modules.trainSeatModule.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.harshchauhan.irctc_core.common.exceptions.ResourceNotFoundException;
import com.harshchauhan.irctc_core.entities.train.TrainSeatModel;
import com.harshchauhan.irctc_core.modules.trainSeatModule.dtos.TrainSeatBookingConfirmationDto;
import com.harshchauhan.irctc_core.modules.trainSeatModule.producer.TrainSeatBookingProducer;
import com.harshchauhan.irctc_core.modules.trainSeatModule.repository.TrainSeatRepository;
import com.harshchauhan.irctc_core.modules.userModule.core.UserAuthDetails;

import jakarta.persistence.PessimisticLockException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrainSeatService {

    private final TrainSeatRepository trainSeatRepository;
    private final TrainSeatAsyncService trainSeatAsyncService;
    private final TrainSeatBookingProducer trainSeatBookingProducer;

    public TrainSeatService(TrainSeatRepository trainSeatRepository, TrainSeatAsyncService trainSeatAsyncService,
            TrainSeatBookingProducer trainSeatBookingProducer) {
        this.trainSeatRepository = trainSeatRepository;
        this.trainSeatAsyncService = trainSeatAsyncService;
        this.trainSeatBookingProducer = trainSeatBookingProducer;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void bookSpecificSeat(UserAuthDetails userId, String trainId, int seatNumber)
            throws InterruptedException, ExecutionException {
        try {
            CompletableFuture<String> trainSeatAsyncTask = trainSeatAsyncService.asyncTask();

            TrainSeatModel seat = trainSeatRepository.findSpecificSeat(trainId, seatNumber)
                    .orElseThrow(() -> new RuntimeException("Seat not found or already booked"));

            if (seat.isBooked()) {
                throw new ResourceNotFoundException("Seat already booked");
            }

            seat.setBooked(true);
            trainSeatRepository.save(seat);

            String asyncTrainSeatTaskMessage = trainSeatAsyncTask.get();

            log.info("\n\n\nTASK COMPLETED :::: {}", asyncTrainSeatTaskMessage);

            trainSeatBookingProducer
                    .sendTrainBookingConfirmation(
                            new TrainSeatBookingConfirmationDto(trainId, seatNumber, userId.getUsername()));

        } catch (PessimisticLockException | CannotAcquireLockException e) {
            throw new RuntimeException("Seat is currently being booked by someone else, please try again.");
        }
    }

}
