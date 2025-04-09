package com.example.demo.utility.responseHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.utility.responseHandler.responseClasses.DataResponse;
import com.example.demo.utility.responseHandler.responseClasses.SuccessResponse;

public class ResponseHandler {
    public static ResponseEntity<SuccessResponse> success(String message, HttpStatus status) {
        return new ResponseEntity<>(SuccessResponse.builder().responseCode(status.value()).message(message).build(),
                status);
    }

    public static <T> ResponseEntity<DataResponse<T>> data(T data, HttpStatus status) {
        return new ResponseEntity<>(DataResponse.<T>builder().responseCode(status.value()).data(data).build(), status);
    }
}
