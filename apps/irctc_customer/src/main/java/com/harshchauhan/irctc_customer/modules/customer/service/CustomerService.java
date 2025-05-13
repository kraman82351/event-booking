package com.harshchauhan.irctc_customer.modules.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.harshchauhan.irctc_customer.externalServices.IrctcCoreService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

    private WebClient.Builder webClientBuilder;
    private IrctcCoreService irctcCoreService;

    public CustomerService(WebClient.Builder webClientBuilder, IrctcCoreService irctcCoreService) {
        this.webClientBuilder = webClientBuilder;
        this.irctcCoreService = irctcCoreService;
    }

    @CircuitBreaker(name = "irctcCoreCircuitBreaker", fallbackMethod = "getMySeatWebClientFallback")
    public Object getMySeatWebClient(String authorizationHeader) {
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
                .bodyToMono(Object.class)
                .block();

        return response;
    }

    public Object getMySeatFeign(String authorizationHeader) {
        Object response = irctcCoreService.getAllTrains(authorizationHeader);

        return response;
    }

    public Object getMySeatWebClientFallback(String token, Throwable throwable) {
        log.warn("Fallback is executed because service is down");
        return "External service is down. Please try again later.";
    }

}
