package com.example.demo.utility.responseHandler;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse {
    private boolean isSuccess = true;
    private String message;
    private HttpStatusCode responseCode;
}
