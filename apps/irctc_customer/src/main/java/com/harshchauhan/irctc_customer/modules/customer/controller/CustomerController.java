package com.harshchauhan.irctc_customer.modules.customer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshchauhan.irctc_customer.modules.customer.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/me")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("seat/web-client")
    public ResponseEntity<Object> getMySeatWebClient(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        Object response = customerService.getMySeatWebClient(authorizationHeader);
        return ResponseEntity.ok(response);
    }

    @GetMapping("seat")
    public ResponseEntity<Object> getMySeat(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        Object response = customerService.getMySeatFeign(authorizationHeader);
        return ResponseEntity.ok(response);
    }

}
