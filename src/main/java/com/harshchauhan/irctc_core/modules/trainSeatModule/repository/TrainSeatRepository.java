package com.harshchauhan.irctc_core.modules.trainSeatModule.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harshchauhan.irctc_core.entities.train.TrainSeatModel;

import jakarta.persistence.LockModeType;

@Repository
public interface TrainSeatRepository extends JpaRepository<TrainSeatModel, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM TrainSeatModel s WHERE s.train.trainId = :trainId AND s.seatNumber = :seatNumber")
    Optional<TrainSeatModel> findSpecificSeat(
            @Param("trainId") String trainId,
            @Param("seatNumber") int seatNumber);
}
