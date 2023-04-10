import java.util.Calendar;


public class CalendarEx {
	public static void hello(int time) {
		if (time >= 4 && time <= 12) {
			System.out.println("Good Morning");
		} else if (time > 12 && time <= 18) {
			System.out.println("Good Afternoon");
		} else if (time >= 18 && time <= 22) {
			System.out.println("Good Evening");
		} else {
			System.out.println("Good Night");
		}
	}
	
	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		int t1 = now.get(Calendar.HOUR_OF_DAY);		// ÇöÀç
		hello(t1);
		
		now.set(Calendar.HOUR_OF_DAY, 6);
		int t2 = now.get(Calendar.HOUR_OF_DAY);
		hello(t2);
		
		now.set(Calendar.HOUR_OF_DAY, 16);
		int t3 = now.get(Calendar.HOUR_OF_DAY);
		hello(t3);
		
		now.set(Calendar.HOUR_OF_DAY, 20);
		int t4 = now.get(Calendar.HOUR_OF_DAY);
		hello(t4);
		
		now.set(Calendar.HOUR_OF_DAY, 24);
		int t5 = now.get(Calendar.HOUR_OF_DAY);
		hello(t5);
	}
}
