package com.harshchauhan.irctc_core.modules.trainModule.service;

import com.harshchauhan.irctc_core.modules.trainModule.response.GetAllTrainsResponse;

public interface TrainService {
    public GetAllTrainsResponse getAllTrains();
    public void createTrainWithSeats(String name, int seatCount);
}
