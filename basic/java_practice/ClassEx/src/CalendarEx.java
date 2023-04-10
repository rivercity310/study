import java.util.Calendar;


public class CalendarEx {
	public static void printCalendar(String msg, Calendar cal) {
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int day = 1;
		
		while (day <= 8) {
			cal.set(2022, 0, day++);
			System.out.println(cal.get(Calendar.DAY_OF_WEEK));		// 일요일: 1 ~ 토요일: 7
			System.out.println(cal.get(Calendar.DAY_OF_MONTH));		
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		printCalendar("현재 ", now);
		
	}
}
