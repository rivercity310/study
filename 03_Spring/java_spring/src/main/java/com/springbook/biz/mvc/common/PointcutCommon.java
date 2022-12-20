package com.springbook.biz.mvc.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
    @Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
    public void allPointcut() { }

    @Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
    public void getPointcut() { }
}
