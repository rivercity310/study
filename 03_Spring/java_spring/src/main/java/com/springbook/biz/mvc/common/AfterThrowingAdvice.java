package com.springbook.biz.mvc.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowingAdvice {
    @AfterThrowing(pointcut = "com.springbook.biz.mvc.common.PointcutCommon.allPointcut()", throwing = "exceptObj")
    public void exceptionLog(JoinPoint jp, Exception exceptObj) {
        String method = jp.getSignature().getName();
        System.out.println("[After Throwing Advice] " + method + "() 수행 중 예외 : " + exceptObj.getMessage());
    }
}
