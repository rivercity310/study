package com.springbook.biz.mvc.common;

import com.springbook.biz.mvc.user.UserVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterReturningAdvice {
    @AfterReturning(pointcut = "com.springbook.biz.mvc.common.PointcutCommon.getPointcut()", returning = "returnObj")
    public void afterLog(JoinPoint jp, Object returnObj) {
        String method = jp.getSignature().getName();

        System.out.println("[After Returning Advice]");

        if (returnObj instanceof UserVO) {
            UserVO user = (UserVO) returnObj;
            if (user.getRole().equals("admin")) System.out.println("- " + user.getName() + " 로그인(Admin)");
        }

        if (returnObj != null) System.out.println("- " + method + "() 리턴값: " + returnObj.toString());
        else System.out.println("- 리턴값 void");
    }
}
