package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
    public static void main(String[] args) {
        /*
        TV tv = new SamsungTV();
        */

        /*
        - BeanFactory 클래스 이용

        BeanFactory factory = new BeanFactory();
        TV tv = (TV)factory.getBean(args[0]);
         */

        /* Spring 컨테이너로부터 필요한 객체를 요청(Look up)한다. */
        AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
        TV tv = (TV)factory.getBean("tv");

        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();
    }
}
