package polymorphism;

public class SeungsuTV implements TV {
    private Speaker speaker;
    private int price;

    public void setSpeaker(Speaker speaker) {
        System.out.println("setSpeaker 호출!");
        this.speaker = speaker;
    }

    public void setPrice(int price) {
        System.out.println("setPrice 호출!");
        this.price = price;
    }
    public void powerOn() {
        System.out.println("SeungsuTV Power On!");
    }

    public void powerOff() {
        System.out.println("SeungsuTV Power Off!");
    }

    public void volumeUp() {
        System.out.println("SeungsuTV Volume Up!");
    }

    public void volumeDown() {
        System.out.println("SeungsuTV Volume Down!");
    }
}
