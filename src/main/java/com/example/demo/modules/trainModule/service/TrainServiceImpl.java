package com.example.demo.modules.trainModule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.constants.CacheKeys;
import com.example.demo.entities.train.TrainModel;
import com.example.demo.entities.train.TrainSeatModel;
import com.example.demo.modules.trainModule.dto.TrainDto;
import com.example.demo.modules.trainModule.repository.TrainRepository;
import com.example.demo.modules.trainModule.response.GetAllTrainsResponse;
import com.example.demo.modules.trainSeatModule.repository.TrainSeatRepository;

@Service
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;
    private final TrainSeatRepository trainSeatRepository;

    public TrainServiceImpl(TrainRepository trainRepository, TrainSeatRepository trainSeatRepository) {
        this.trainRepository = trainRepository;
        this.trainSeatRepository = trainSeatRepository;
    }

    @Cacheable(value = CacheKeys.Train.MODULE, key = CacheKeys.Train.GET_ALL_TRAINS)
    @Override
    public synchronized GetAllTrainsResponse getAllTrains() {
        List<TrainDto> trains = trainRepository.findAll().stream().map(train -> {
            TrainDto trainDto = new TrainDto();
            trainDto.setName(train.getName());
            trainDto.setTrainId(train.getTrainId());

            return trainDto;
        }).collect(Collectors.toList());

        return new GetAllTrainsResponse(trains);
    }

    @Override
    @Transactional
    // @CachePut(value = CacheKeys.Train.MODULE, key = CacheKeys.Train.GET_ALL_TRAINS)
    @CacheEvict(value = CacheKeys.Train.MODULE, key = CacheKeys.Train.GET_ALL_TRAINS)
    public void createTrainWithSeats(String name, int seatCount) {
        TrainModel train = new TrainModel();
        train.setName(name);

        trainRepository.save(train);

        for (int i = 0; i < seatCount; i++) {
            TrainSeatModel trainSeat = new TrainSeatModel();
            trainSeat.setTrain(train);
            trainSeat.setSeatNumber(i + 1);
            trainSeatRepository.save(trainSeat);
        }
    }
}
