package com.example.java_spring.polymorphism;

import com.example.java_spring.polymorphism.tv.TV;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
    public static void main(String[] args) {
        /*
        // Factory 패턴 + 명령행 인자를 통해 클라이언트 소스를 수정하지 않고 실행되는 객체 변경 가능
        BeanFactory factory = new BeanFactory();
        TV tv = (TV) factory.getBean(args[0]);
        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();
        */

        /* 스프링 IoC : 일련의 작업들을 컨테이너로 처리 (객체 생성, 메서드 호출 등..) */
        AbstractApplicationContext factory =
                new GenericXmlApplicationContext("applicationContext.xml");

        // 스프링 컨테이너에 필요한 객체 요청 (Look up) --> Dependency Lookup 방식, 실제 과정에서는 Dependency Injection 방식을 사용한다.
        TV tv1 = (TV) factory.getBean("samsungTV");


        tv1.powerOn();
        tv1.volumeUp();

        factory.close();
    }
}
