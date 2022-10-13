package jspbean;

public class CountVO {
    private int number;

    public CountVO() {
        this.number = 0;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int n) {
        this.number += n;
    }
}