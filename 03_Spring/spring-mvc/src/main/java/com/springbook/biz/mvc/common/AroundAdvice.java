package com.springbook.biz.mvc.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {
    @Around("com.springbook.biz.mvc.common.PointcutCommon.allPointcut()")
    public Object aroundAdvLog(ProceedingJoinPoint pjp) throws Throwable {
        String method = pjp.getSignature().getName();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object obj = pjp.proceed();

        stopWatch.stop();
        System.out.println("[Around Advice] " + method + "() 수행에 걸린 시간 : " + stopWatch.getTotalTimeMillis() + "ms");
        return obj;
    }
}
