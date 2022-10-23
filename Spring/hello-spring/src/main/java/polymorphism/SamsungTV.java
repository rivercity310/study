package polymorphism;

public class SamsungTV implements TV {
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
