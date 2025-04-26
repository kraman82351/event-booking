package com.harshchauhan.irctc_core.modules.userModule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshchauhan.irctc_core.modules.userModule.request.UserTestApiRequest;
import com.harshchauhan.irctc_core.modules.userModule.service.UserService;
import com.harshchauhan.irctc_core.modules.userModule.service.UserServiceImpl;
import com.harshchauhan.irctc_core.utility.responseHandler.ResponseHandler;
import com.harshchauhan.irctc_core.utility.responseHandler.responseClasses.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
@Tag(name = "User API", description = "Operations related to user management")
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Operation(summary = "Hello world test", description = "Simple endpoint to verify controller is up", responses = {
            @ApiResponse(responseCode = "200", description = "Request successful", content = @Content(schema = @Schema(implementation = SuccessResponse.class)))
    })
    @GetMapping("/hello")
    public ResponseEntity<SuccessResponse> helloWorld() {
        String message = "Hello World!";
        return ResponseHandler.success(message, HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity<SuccessResponse> testing(@Valid @RequestBody UserTestApiRequest userTestApiRequest) {
        String message = "Success!";
        return ResponseHandler.success(message, HttpStatus.OK);
    }

}
