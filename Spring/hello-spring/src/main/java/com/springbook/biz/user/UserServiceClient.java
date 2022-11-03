package com.springbook.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserServiceClient {
    private static final BufferedReader br
            = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 1. Spring Container 구동
        AbstractApplicationContext container =
                new GenericXmlApplicationContext("applicationContext.xml");

        // 2. Spring Container 로부터 UserServiceImpl 객체를 Lookup 한다.
        UserService userService = (UserService) container.getBean("userService");

        // 3. 로그인 기능 Test
        UserVO vo = new UserVO();

        System.out.print("ID: ");
        String uid = br.readLine();

        System.out.print("PW: ");
        String upw = br.readLine();

        // Validation Check 후에
        vo.setId(uid);
        vo.setPassword(upw);

        UserVO user = userService.getUser(vo);
        if (user != null) {
            // Todo Sth if Login Success..
            System.out.println(user.getName() + "님 환영합니다!");
        }

        else {
            // Todo Sth if Login Failed...
            System.out.println("Login Fail");
        }

        // 4. Container Close
        container.close();
        br.close();
    }
}
