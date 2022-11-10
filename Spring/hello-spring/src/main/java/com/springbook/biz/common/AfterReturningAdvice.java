package com.springbook.biz.common;

import com.springbook.biz.user.UserVO;
import org.aspectj.lang.JoinPoint;

public class AfterReturningAdvice {

    public void afterLog(JoinPoint jp, Object returnObj) {
        String methodName = jp.getSignature().getName();

        if (returnObj instanceof UserVO) {
            UserVO user = (UserVO)returnObj;

            if (user.getRole().equals("Admin"))
                System.out.println(user.getName() + " 로그인(Admin)");
        }

        System.out.println("[사후 처리] " + "Method Name: " + methodName + ", " + "메소드 리턴값: " + returnObj.toString());
    }
}
