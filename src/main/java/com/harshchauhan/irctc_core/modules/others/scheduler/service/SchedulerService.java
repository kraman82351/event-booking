package com.harshchauhan.irctc_core.modules.others.scheduler.service;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.harshchauhan.irctc_core.modules.schedulerLogModule.service.SchedulerLogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SchedulerService {

    private final SchedulerLogService schedulerLogService;

    public SchedulerService(SchedulerLogService schedulerLogService) {
        this.schedulerLogService = schedulerLogService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void schedulerCronJob() {
        log.info("Logging in scheduler : ", LocalDateTime.now().toString());
        schedulerLogService.log(LocalDateTime.now().toString());
    }
}
