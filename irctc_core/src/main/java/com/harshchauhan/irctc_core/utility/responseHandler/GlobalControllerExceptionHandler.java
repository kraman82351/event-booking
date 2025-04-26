package com.harshchauhan.irctc_core.utility.responseHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.harshchauhan.irctc_core.common.exceptions.ResourceNotFoundException;
import com.harshchauhan.irctc_core.utility.responseHandler.responseClasses.FailureResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<FailureResponse> handleHttpMessageNotResponseException(
                        final HttpMessageNotReadableException exception) {

                String errorMessage = "Malformed JSON request";

                if (exception.getCause() instanceof InvalidFormatException formatException) {
                        errorMessage = "Invalid value for field - " + formatException.getPath().get(0).getFieldName()
                                        + ": "
                                        + formatException.getValue() + " - " + formatException.getOriginalMessage();
                }

                return ResponseHandler.failure("Error", "Bad Request",
                                HttpStatus.BAD_REQUEST, errorMessage, "E002");
        }

        @ResponseStatus(HttpStatus.FORBIDDEN)
        @ExceptionHandler(AuthenticationException.class)
        public ResponseEntity<FailureResponse> handleAuthenticationException(
                        final AuthenticationException exception) {

                String errorMessage = "Forbidden";

                return ResponseHandler.failure("Forbidden", "Forbidden",
                                HttpStatus.FORBIDDEN, errorMessage, "E004");
        }

        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity<FailureResponse> handleBadCredentialsException(
                        final BadCredentialsException exception) {

                String errorMessage = "Bad Credentials";

                return ResponseHandler.failure("Invalid Credentials", "Invalid Credentials",
                                HttpStatus.UNAUTHORIZED, errorMessage, "E003");
        }

        @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<FailureResponse> handleResourceNotFoundException(
                        final ResourceNotFoundException exception) {

                String errorMessage = "Resource Not Found";

                return ResponseHandler.failure(
                                "Resource Not Found",
                                "Resource Not Found",
                                HttpStatus.UNPROCESSABLE_ENTITY,
                                errorMessage, "E004");
        }

        @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<FailureResponse> handleMethodArgumentNotValidException(
                        final MethodArgumentNotValidException exception) {

                Map<String, String> errors = new HashMap<>();

                exception.getBindingResult().getFieldErrors()
                                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

                System.out.println(exception);

                return ResponseHandler.failure("Error", "Validation Error",
                                HttpStatus.UNPROCESSABLE_ENTITY, errors, "E002");
        }

        @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
        @ExceptionHandler(Exception.class)
        public ResponseEntity<FailureResponse> handleInternalServerException(final Exception exception) {
                return ResponseHandler.failure("Error", exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null,
                                "E001");
        }

}
