package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;

public class AfterThrowingAdvice {
    public void exceptionLog(JoinPoint jp, Exception exceptObj) {
        String methodName = jp.getSignature().getName();
        System.out.println("[예외 처리] " + methodName + ", 메소드 수행중 발생된 예외 메세지: " + exceptObj.getMessage());

        if (exceptObj instanceof IllegalArgumentException)
            System.out.println("부적합한 값 입력");
        else if (exceptObj instanceof NumberFormatException)
            System.out.println("숫자 형식의 값 아님");
        else if (exceptObj instanceof Exception)
            System.out.println("문제가 발생했습니다...");
    }
}
