package com.springbook.biz.common;

public class Log4jAdvice {

    public Log4jAdvice() {
        System.out.println("Log4J 객체 생성");
    }

    public void printLogging() {
        System.out.println("[Common Log - Log4j] Starting before Business Logic Execution...");
    }
}
