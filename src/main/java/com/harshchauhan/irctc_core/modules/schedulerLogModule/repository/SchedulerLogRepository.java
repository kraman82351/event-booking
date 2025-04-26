package com.harshchauhan.irctc_core.modules.schedulerLogModule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshchauhan.irctc_core.entities.schedulerLog.SchedulerLogModel;

public interface SchedulerLogRepository extends JpaRepository<SchedulerLogModel, Integer> {

}
