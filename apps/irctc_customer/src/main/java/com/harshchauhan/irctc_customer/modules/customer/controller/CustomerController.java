package com.harshchauhan.irctc_customer.modules.customer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.harshchauhan.irctc_customer.externalServices.IrctcCoreService;

import brave.Response;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/me")
public class CustomerController {

    private WebClient.Builder webClientBuilder;
    private IrctcCoreService irctcCoreService;

    public CustomerController(WebClient.Builder webClientBuilder, IrctcCoreService irctcCoreService) {
        this.webClientBuilder = webClientBuilder;
        this.irctcCoreService = irctcCoreService;
    }

    @GetMapping("seat/web-client")
    @CircuitBreaker(name = "irctcCoreCircuitBreaker", fallbackMethod = "getMySeatWebClientFallback")
    public ResponseEntity<Object> getMySeatWebClient(HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        Object response = webClientBuilder
                .build()
                .get()
                .uri("http://irctc-core/train")
                .headers(headers -> {
                    if (authorizationHeader != null && !authorizationHeader.isEmpty()) {
                        headers.set("Authorization", authorizationHeader);
                    }
                })
                .retrieve()
                .bodyToMono(Object.class).block();

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> getMySeatWebClientFallback(Exception exception) {
        log.warn("Fallback is executed because service is down :: ", exception.getMessage());
        return ResponseEntity.badRequest().body("External service is down. Please try again later.");
    }

    @GetMapping("seat")
    public Object getMySeat(HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        Object response = irctcCoreService.getAllTrains(authorizationHeader);

        return ResponseEntity.ok(response);
    }

}
