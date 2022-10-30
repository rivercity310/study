package polymorphism;

import org.springframework.stereotype.Component;

@Component
public class SamsungTV implements TV {
    private Speaker speaker;
    private int price;

    public void initMethod() {
        System.out.println("객체 초기화 작업 처리....");
    }

    public void destroyMethod() {
        System.out.println("객체 삭제 전에 처리할 로직 처리...");
    }
    public SamsungTV() {
        System.out.println("SamsungTV(1) -- 객체 생성");
    }

    public SamsungTV(Speaker speaker, int price) {
        System.out.println("SamsungTV(2) -- 객체 생성");
        this.speaker = speaker;
        this.price = price;
    }
    public void powerOn() {
        System.out.printf("SamsungTV -- Power On\t(가격: %d)\n", price);
    }

    public void powerOff() {
        System.out.println("SamsungTV -- Power Off");
    }


    public void volumeUp() {
        speaker.volumeUp();
    }

    public void volumeDown() {
        speaker.volumeDown();
    }
}
