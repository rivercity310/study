package com.springbook.biz.mvc.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterAdvice {
    @After("com.springbook.biz.mvc.common.PointcutCommon.allPointcut()")
    public void finallyLog() {
        System.out.println("[After Advice]");
    }
}
