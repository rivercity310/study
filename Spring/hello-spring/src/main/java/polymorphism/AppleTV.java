package polymorphism;

public class AppleTV implements TV {
    private Speaker speaker;
    private int price;

    public AppleTV() {
        System.out.println("AppleTV -- 객체 생성");
    }

    public void setSpeaker(Speaker speaker) {
        System.out.println("==> setSpeaker 호출!");
        this.speaker = speaker;
    }

    public void setPrice(int price) {
        System.out.println("==> setPrice 호출!");
        this.price = price;
    }

    @Override
    public void powerOn() {
        System.out.println("AppleTV Power On!");
    }

    @Override
    public void powerOff() {
        System.out.println("AppleTV Power Off!");
    }

    @Override
    public void volumeUp() {
        System.out.println("AppleTV Volume Up!");
    }

    @Override
    public void volumeDown() {
        System.out.println("AppleTV Volume Down!");
    }
}
