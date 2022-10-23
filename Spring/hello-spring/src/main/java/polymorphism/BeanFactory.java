package polymorphism;

/*
Factory 패턴: 클라이언트에서 사용할 객체 생성을 캡슐화하여 TVUser와 TV 사이의 결합도를 느슨하게 만들어주는 패턴
 */

public class BeanFactory {
    public Object getBean(String beanName) {
        if (beanName.equals("samsung"))
            return new SamsungTV();
        else if (beanName.equals("lg"))
            return new LgTV();

        return null;
    }
}
