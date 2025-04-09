package com.example.demo.utility.responseHandler;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse<T> {
    private boolean isSuccess = true;
    private T data;
    private HttpStatus responseCode;
}
