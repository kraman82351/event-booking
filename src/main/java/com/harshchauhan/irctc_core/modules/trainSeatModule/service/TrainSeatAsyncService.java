package com.harshchauhan.irctc_core.modules.trainSeatModule.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrainSeatAsyncService {

    @Async
    public CompletableFuture<String> asyncTask() {
        log.info("Async train ticket booking task initiated. Thread name {}", Thread.currentThread().getName());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Async task interrupted", e);
        }

        log.info("Async train ticket booking task completed. Thread name {}", Thread.currentThread().getName());

        return CompletableFuture.completedFuture("Task completed by Thread : " + Thread.currentThread().getName());
    }

}
