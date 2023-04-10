package chapter4_Class;

public interface Focusable_java {
    default void setFocus(boolean b) {
        String stat = b ? "got" : "lost";
        System.out.println("i " + stat + " focus!");
    }
    default void showOff() {
        System.out.println("I'm Focusable_Java");
    }
}
