package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component
public class LogAdvice {
    public void printLog(JoinPoint jp) {
        System.out.println("[Common Log] Starting before Business Logic execution...");
    }
}
