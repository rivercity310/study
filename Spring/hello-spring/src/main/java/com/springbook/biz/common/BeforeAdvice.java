package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;

public class BeforeAdvice {
    public void beforeLog(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        System.out.println("[Before Advice] " + methodName + "\n" + "메소드 인자 정보: " + args[0].toString());
        System.out.println(jp.getSignature().toLongString());
        System.out.println(jp.getSignature().toString());
        System.out.println(jp.getSignature().toShortString());
    }
}
