package com.harshchauhan.irctc_core.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class MeasureExecutionTimeAspect {

    @Around("@annotation(com.harshchauhan.irctc_core.common.annotations.MeasureExecutionTime)")
    public Object measureExecutionTime(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        Long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        Long end = System.currentTimeMillis();

        log.info(
                "\n\n[" + proceedingJoinPoint.getSignature() + "]" + " Execution time is :: " + (end - start) + "ms\n");

        return result;
    }

}
