package com.example.java_spring.polymorphism;

import com.example.java_spring.polymorphism.tv.LgTV;
import com.example.java_spring.polymorphism.tv.SamsungTV;

/* ex) 결합도를 낮추기 위한 Factory 디자인 패턴 */
/*
public class BeanFactory {
    public Object getBean(String beanName) {
        if (beanName.equals("samsung"))
            return new SamsungTV();

        else if (beanName.equals("lg"))
            return new LgTV();

        return null;
    }
}
*/
