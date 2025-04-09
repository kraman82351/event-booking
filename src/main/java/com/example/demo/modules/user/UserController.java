package com.example.demo.modules.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.utility.responseHandler.ResponseHandler;
import com.example.demo.utility.responseHandler.responseClasses.SuccessResponse;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping()
    public ResponseEntity<SuccessResponse> helloWorld() {
        String message = "Hello World!";
        return ResponseHandler.success(message, HttpStatus.OK);
    }

}
