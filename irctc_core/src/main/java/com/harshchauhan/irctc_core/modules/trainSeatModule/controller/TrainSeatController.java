package com.harshchauhan.irctc_core.modules.trainSeatModule.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshchauhan.irctc_core.common.annotations.MeasureExecutionTime;
import com.harshchauhan.irctc_core.modules.trainSeatModule.request.BookTrainSeatRequest;
import com.harshchauhan.irctc_core.modules.trainSeatModule.service.TrainSeatService;
import com.harshchauhan.irctc_core.modules.userModule.core.UserAuthDetails;
import com.harshchauhan.irctc_core.utility.responseHandler.ResponseHandler;
import com.harshchauhan.irctc_core.utility.responseHandler.responseClasses.SuccessResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/train-seat")
public class TrainSeatController {

    private final TrainSeatService trainSeatService;

    public TrainSeatController(TrainSeatService trainSeatService) {
        this.trainSeatService = trainSeatService;
    }

    @PostMapping("/book")
    @MeasureExecutionTime
    public ResponseEntity<SuccessResponse> bookTrainSeat(
            @Valid @RequestBody BookTrainSeatRequest bookTrainSeatRequest)
            throws InterruptedException, ExecutionException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserAuthDetails userAuthDetails = (UserAuthDetails) authentication.getPrincipal();

        trainSeatService.bookSpecificSeat(
                userAuthDetails,
                bookTrainSeatRequest.getTrainId(),
                bookTrainSeatRequest.getSeatNumber());

        return ResponseHandler.success("Booked Seat", HttpStatus.OK);
    }
}
