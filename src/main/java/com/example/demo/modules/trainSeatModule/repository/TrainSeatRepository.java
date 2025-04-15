package com.example.demo.modules.trainSeatModule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.train.TrainSeatModel;

@Repository
public interface TrainSeatRepository extends JpaRepository<TrainSeatModel, Long> {
}
