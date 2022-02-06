import java.util.Scanner;
import java.util.Calendar;

class Player {
	private String name;
	private int t;
	private Calendar now; 
	
	public Player(String n) {
		this.name = n;
	}
	public String getName() {
		return this.name;
	}
	public void setTime(int t) {
		this.t = t;
	}
	public int getTime() {
		return this.t;
	}
	public Calendar getCalendar() {
		now = Calendar.getInstance();
		return this.now;
	}
}

public class CalendarGame {
	private static Player[] players;
	private static Scanner s;
	final private static int TEN = 10;
	
	private static void initGame() {
		s = new Scanner(System.in);
		System.out.println("10초에 가까운 사람이 이기는 게임입니다.");
		System.out.print("참가 인원을 입력하세요: ");
		int n = s.nextInt();
		players = new Player[n];
		
		for (int i = 0; i < n; i++) {
			System.out.printf("%d번째 이름: ", i + 1);
			String name = s.next();
			players[i] = new Player(name);
		}
		
		// 남아있는 개행문자 제거
		s.nextLine();	
	}
	
	private static void run() {
		
		for (int i = 0; i < players.length; i++) {
			System.out.printf("%s 시작 <Enter>키", players[i].getName());
			s.nextLine();
			int prev = players[i].getCalendar().get(Calendar.SECOND);
			System.out.printf("현재 초 시간: %d\n", prev);
			
			System.out.print("10초 예상 후 <Enter>키");
			s.nextLine();
			int next = players[i].getCalendar().get(Calendar.SECOND);
			System.out.printf("현재 초 시간: %d\n", next);
			
			players[i].setTime(next - prev);
		}
	}
	
	private static void printWinner() {
		String name = null;
		int pTime = 10;
	
		for (int i = 0; i < players.length; i++) {
			System.out.printf("%s의 결과: %d\n", players[i].getName(), players[i].getTime());
			if (Math.abs(TEN - players[i].getTime()) < pTime) {
				name = players[i].getName();
			}
		}
		
		System.out.printf("승자는 %s", name);
		s.close();
		
	}
	
	public static void main(String[] args) {
		initGame();
		run();
		printWinner();
	}
}
