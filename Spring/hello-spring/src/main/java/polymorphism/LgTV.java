package polymorphism;

public class LgTV implements TV {
    public void powerOn() {
        System.out.println("LgTV -- Power On");
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
