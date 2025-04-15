package com.example.demo.modules.userModule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modules.userModule.request.CreateUserApiRequest;
import com.example.demo.modules.userModule.request.UserLoginRequest;
import com.example.demo.modules.userModule.request.UserTestApiRequest;
import com.example.demo.modules.userModule.service.UserService;
import com.example.demo.modules.userModule.service.UserServiceImpl;
import com.example.demo.utility.responseHandler.ResponseHandler;
import com.example.demo.utility.responseHandler.responseClasses.SuccessResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
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
