package com.redbard.scim.profiler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ProfilerAspect {

    @Around("@annotation(profile)")
    public Object doBasicProfiling(ProceedingJoinPoint pjp, Profile profile) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } catch (Throwable t) {
            throw t;
        } finally {
            log.info(profile.value() + ": " + (System.currentTimeMillis() - startTime) + "ms");
        }
    }
}