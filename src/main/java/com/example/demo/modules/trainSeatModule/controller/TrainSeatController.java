package com.example.demo.modules.trainSeatModule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.common.annotations.MeasureExecutionTime;
import com.example.demo.modules.trainSeatModule.request.BookTrainSeatRequest;
import com.example.demo.modules.trainSeatModule.service.TrainSeatService;
import com.example.demo.modules.userModule.core.UserAuthDetails;
import com.example.demo.utility.responseHandler.ResponseHandler;
import com.example.demo.utility.responseHandler.responseClasses.SuccessResponse;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/train-seat")
public class TrainSeatController {

    private final TrainSeatService trainSeatService;

    public TrainSeatController(TrainSeatService trainSeatService) {
        this.trainSeatService = trainSeatService;
    }

    @PostMapping("/book")
    @MeasureExecutionTime
    public ResponseEntity<SuccessResponse> bookTrainSeat(
            @Valid @RequestBody BookTrainSeatRequest bookTrainSeatRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserAuthDetails userAuthDetails = (UserAuthDetails) authentication.getPrincipal();

        trainSeatService.bookSpecificSeat(
                userAuthDetails.getId(),
                bookTrainSeatRequest.getTrainId(),
                bookTrainSeatRequest.getSeatNumber());

        return ResponseHandler.success("Booked Seat", HttpStatus.OK);
    }
}
