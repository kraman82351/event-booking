package com.example.demo.modules.trainModule.service;

import com.example.demo.modules.trainModule.response.GetAllTrainsResponse;

public interface TrainService {
    public GetAllTrainsResponse getAllTrains();
    public void createTrainWithSeats(String name, int seatCount);
}
