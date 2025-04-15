package com.example.demo.modules.trainModule.seeder;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entities.train.TrainModel;
import com.example.demo.modules.trainModule.repository.TrainRepository;
import com.example.demo.modules.trainModule.service.TrainService;
import com.example.demo.modules.trainModule.service.TrainServiceImpl;

@Component
public class TrainDatabaseSeeder implements CommandLineRunner {

    private final TrainRepository trainRepository;
    private final TrainService trainService;

    public TrainDatabaseSeeder(TrainRepository trainRepository, TrainServiceImpl trainService) {
        this.trainRepository = trainRepository;
        this.trainService = trainService;
    }

    @Override
    public void run(String... args) {
        upsertTrains("Rajdhani Express");
        // upsertTrains("Shatabdi Express");
        System.out.println("Train data seeded successfully!");
    }

    private void upsertTrains(String trainName) {
        Optional<TrainModel> train = trainRepository.findByName(trainName);

        if (train.isEmpty()) {
            trainService.createTrainWithSeats(trainName, 10);
        }
    }
}
