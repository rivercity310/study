package polymorphism;

public class LgTV implements TV {
    private Speaker speaker;
    private int price;

    public LgTV() {
        System.out.println("LgTV(1) -- 객체 생성");
    }

    public LgTV(Speaker speaker, int price) {
        System.out.println("LgTV(2) -- 객체 생성");
        this.speaker = speaker;
        this.price = price;
    }
    public void powerOn() {
        System.out.printf("LgTV -- Power On\t(가격: %d)\n", price);
    }

    public void powerOff() {
        System.out.println("LgTV -- Power Off");
    }

    public void volumeUp() {
        System.out.println("LgTV -- Volume Up");
    }

    public void volumeDown() {
        System.out.println("LgTV -- Volume Down");
    }
}
