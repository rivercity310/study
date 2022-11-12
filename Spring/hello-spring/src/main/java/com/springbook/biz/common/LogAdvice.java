package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LogAdvice {
    /*
    // 포인트컷 식별을 위한 참조 메소드
    @Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
    public void allPointcut() { }

    @Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
    public void getPointcut() { }

    @Before("allPointcut()")
    public void printLog(JoinPoint jp) {
        System.out.println("[Common Log] Starting before Business Logic execution...");
    }

    @AfterReturning("getPointcut()")
    public void printAfterLog(JoinPoint jp) {
        System.out.println(jp.getSignature().getName());
    }
    */
}
