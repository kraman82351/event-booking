package com.example.demo.modules.schedulerLogModule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.schedulerLog.SchedulerLogModel;

public interface SchedulerLogRepository extends JpaRepository<SchedulerLogModel, Integer> {

}
