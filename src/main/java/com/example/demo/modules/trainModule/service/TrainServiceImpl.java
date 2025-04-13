package com.example.demo.modules.trainModule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.modules.trainModule.dto.TrainDto;
import com.example.demo.modules.trainModule.repository.TrainRepository;
import com.example.demo.modules.trainModule.response.GetAllTrainsResponse;

@Service
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;

    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public GetAllTrainsResponse getAllTrains() {
        List<TrainDto> trains = trainRepository.findAll().stream().map(train -> {
            TrainDto trainDto = new TrainDto();
            trainDto.setName(train.getName());
            trainDto.setTrainId(train.getTrainId());

            return trainDto;
        }).collect(Collectors.toList());

        return new GetAllTrainsResponse(trains);
    }
}
