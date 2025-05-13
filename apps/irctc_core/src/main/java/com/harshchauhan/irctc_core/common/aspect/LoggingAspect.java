package com.harshchauhan.irctc_core.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("Before executing method: " + className + "." + methodName + "()");

        // You can also log parameters if needed
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            System.out.println("Method arguments:");
            for (int i = 0; i < args.length; i++) {
                System.out.println("  arg[" + i + "]: " + args[i]);
            }
        }
    }

    @Before("execution(* com.harshchauhan.irctc_core.modules.trainModule..*.*(..))")
    public void logBeforeTrainModule(JoinPoint joinPoint) {
        // logBefore(joinPoint);
    }
}
