package com.harshchauhan.irctc_core.modules.trainSeatModule.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.harshchauhan.irctc_core.common.exceptions.ResourceNotFoundException;
import com.harshchauhan.irctc_core.entities.train.TrainSeatModel;
import com.harshchauhan.irctc_core.modules.trainSeatModule.repository.TrainSeatRepository;

import jakarta.persistence.PessimisticLockException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrainSeatService {

    private final TrainSeatRepository trainSeatRepository;
    private final TrainSeatAsyncService trainSeatAsyncService;

    public TrainSeatService(TrainSeatRepository trainSeatRepository, TrainSeatAsyncService trainSeatAsyncService) {
        this.trainSeatRepository = trainSeatRepository;
        this.trainSeatAsyncService = trainSeatAsyncService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void bookSpecificSeat(Long userId, String trainId, int seatNumber)
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

        } catch (PessimisticLockException | CannotAcquireLockException e) {
            throw new RuntimeException("Seat is currently being booked by someone else, please try again.");
        }
    }

}
