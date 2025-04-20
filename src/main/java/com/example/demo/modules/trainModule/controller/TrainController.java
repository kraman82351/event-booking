package com.example.demo.modules.trainModule.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modules.trainModule.request.CreateTrainRequest;
import com.example.demo.modules.trainModule.response.GetAllTrainsResponse;
import com.example.demo.modules.trainModule.service.TrainService;
import com.example.demo.modules.trainModule.service.TrainServiceImpl;
import com.example.demo.utility.responseHandler.ResponseHandler;
import com.example.demo.utility.responseHandler.responseClasses.DataResponse;
import com.example.demo.utility.responseHandler.responseClasses.SuccessResponse;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/train")
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainServiceImpl trainService) {
        this.trainService = trainService;
    }

    @GetMapping()
    public ResponseEntity<DataResponse<GetAllTrainsResponse>> getAllTrains() {

        log.info("Received request for get all trains");

        GetAllTrainsResponse trains = trainService.getAllTrains();

        log.info("All trains fetched :: " , trains);

        return ResponseHandler.data(trains, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<SuccessResponse> createTrainWithSeats(@Valid @RequestBody CreateTrainRequest createTrainRequest) {
        trainService.createTrainWithSeats(createTrainRequest.getName(), createTrainRequest.getNumberOfSeats());
        return ResponseHandler.success("Successfully created train", HttpStatus.CREATED);
    }

}
