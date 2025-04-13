package com.example.demo.modules.trainModule.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.train.TrainModel;

@Repository
public interface TrainRepository extends JpaRepository<TrainModel, String> {
    Optional<TrainModel> findByName(String name);
}
