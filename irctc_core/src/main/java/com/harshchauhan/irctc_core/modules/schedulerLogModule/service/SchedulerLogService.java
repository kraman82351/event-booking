package com.harshchauhan.irctc_core.modules.schedulerLogModule.service;

import org.springframework.stereotype.Service;

import com.harshchauhan.irctc_core.entities.schedulerLog.SchedulerLogModel;
import com.harshchauhan.irctc_core.modules.schedulerLogModule.repository.SchedulerLogRepository;

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
