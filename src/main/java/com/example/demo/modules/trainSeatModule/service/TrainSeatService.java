package com.example.demo.modules.trainSeatModule.service;

import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.exceptions.ResourceNotFoundException;
import com.example.demo.entities.train.TrainSeatModel;
import com.example.demo.modules.trainSeatModule.repository.TrainSeatRepository;

import jakarta.persistence.PessimisticLockException;

@Service
public class TrainSeatService {

    private final TrainSeatRepository trainSeatRepository;

    public TrainSeatService(TrainSeatRepository trainSeatRepository) {
        this.trainSeatRepository = trainSeatRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void bookSpecificSeat(Long userId, String trainId, int seatNumber) {
        try {

            TrainSeatModel seat = trainSeatRepository.findSpecificSeat(trainId, seatNumber)
                    .orElseThrow(() -> new RuntimeException("Seat not found or already booked"));

            if (seat.isBooked()) {
                throw new ResourceNotFoundException("Seat already booked");
            }

            seat.setBooked(true);
            trainSeatRepository.save(seat);

        } catch (PessimisticLockException | CannotAcquireLockException e) {
            throw new RuntimeException("Seat is currently being booked by someone else, please try again.");
        }
    }

}
