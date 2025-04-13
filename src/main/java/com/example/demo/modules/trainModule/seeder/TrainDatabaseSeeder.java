package com.example.demo.modules.trainModule.seeder;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entities.train.TrainModel;
import com.example.demo.modules.trainModule.repository.TrainRepository;

@Component
public class TrainDatabaseSeeder implements CommandLineRunner {

    private final TrainRepository trainRepository;

    public TrainDatabaseSeeder(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public void run(String... args) {

        upsertTrains("Rajdhani Express");
        upsertTrains("Shatabdi Express");

        System.out.println("Train data seeded successfully!");
    }

    private void upsertTrains(String trainName) {

        Optional<TrainModel> train = trainRepository.findByName(trainName);

        if (train.isEmpty()) {
            TrainModel newTrain = new TrainModel();
            newTrain.setName(trainName);

            trainRepository.save(newTrain);
        }

    }
}
