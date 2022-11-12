package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {
    @Around("PointcutCommon.allPointcut()")
    public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();

        System.out.println("[ Around ] Stop Watch Start");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object obj = pjp.proceed();

        stopWatch.stop();

        System.out.println("[ Around ] Method Name: " + methodName + ", 수행 시간: " + stopWatch.getTotalTimeMillis() + "ms");

        return obj;
    }
}
