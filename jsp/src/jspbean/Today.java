package jspbean;


import java.time.LocalDateTime;

public class Today {
    int year;
    int month;
    int date;

    public Today() {
        LocalDateTime ldt = LocalDateTime.now();
        this.year = ldt.getYear();
        this.month = ldt.getMonthValue();
        this.date = ldt.getDayOfMonth();
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDate() {
        return this.date;
    }
}
