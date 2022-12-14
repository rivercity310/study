package chapter4_Class;

public interface Clickable_java {
    default void showOff() {
        System.out.println("I'm Clickable_Java");
    }
    void click();
}

