package com.example.demo.modules.schedulerLogModule.service;

import org.springframework.stereotype.Service;

import com.example.demo.entities.schedulerLog.SchedulerLogModel;
import com.example.demo.modules.schedulerLogModule.repository.SchedulerLogRepository;

@Service
public class SchedulerLogService {

    private final SchedulerLogRepository schedulerLogRepository;

    public SchedulerLogService(SchedulerLogRepository schedulerLogRepository) {
        this.schedulerLogRepository = schedulerLogRepository;
    }

    public SchedulerLogModel log(String logName) {
        SchedulerLogModel schedulerLogModel = schedulerLogRepository
                .save(SchedulerLogModel.builder().name(logName).build());

        return schedulerLogModel;
    }
}
