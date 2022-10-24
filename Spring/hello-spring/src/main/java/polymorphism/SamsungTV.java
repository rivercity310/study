package polymorphism;

public class SamsungTV implements TV {
    public void initMethod() {
        System.out.println("객체 초기화 작업 처리....");
    }

    public void destroyMethod() {
        System.out.println("객체 삭제 전에 처리할 로직 처리...");
    }
    public SamsungTV() {
        System.out.println("SamsungTV -- 객체 생성");
    }
    public void powerOn() {
        System.out.println("SamsungTV -- Power On");
    }

    public void powerOff() {
        System.out.println("SamsungTV -- Power Off");
    }

    public void volumeUp() {
        System.out.println("SamsungTV -- Volume Up");
    }

    public void volumeDown() {
        System.out.println("SamsungTV -- Volume Down");
    }
}
