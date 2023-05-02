package com.check24.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class LoggerAspects {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.check24.*.*(..))")
    public void logMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        LOG.info("Method executed{} with params {} : " + joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        LOG.info("Value returned {}", result);
    }
}
