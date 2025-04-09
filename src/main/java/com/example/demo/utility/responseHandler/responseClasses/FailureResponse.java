package com.example.demo.utility.responseHandler;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FailureResponse {
    private boolean isSuccess = false;
    private String message;
    private Object error;
    private HttpStatus responseCode;
    private Object data;
    private String errorCode;
}
