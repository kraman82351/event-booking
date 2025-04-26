package com.harshchauhan.irctc_core.utility.responseHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import com.harshchauhan.irctc_core.utility.responseHandler.responseClasses.DataResponse;
import com.harshchauhan.irctc_core.utility.responseHandler.responseClasses.FailureResponse;
import com.harshchauhan.irctc_core.utility.responseHandler.responseClasses.SuccessResponse;

public class ResponseHandler {
    public static ResponseEntity<SuccessResponse> success(String message, HttpStatus status) {
        return new ResponseEntity<>(SuccessResponse.builder().responseCode(status.value()).message(message).build(),
                status);
    }

    public static <T> ResponseEntity<DataResponse<T>> data(T data, HttpStatus status) {
        return new ResponseEntity<>(DataResponse.<T>builder().responseCode(status.value()).data(data).build(), status);
    }

    public static ResponseEntity<FailureResponse> failure(String message,
            Object error,
            HttpStatus status,
            @Nullable Object data,
            @Nullable String errorCode) {
        return new ResponseEntity<>(
                FailureResponse.builder().responseCode(status.value()).message(message).error((error)).data(data)
                        .errorCode(errorCode).build(),
                status);
    }
}
