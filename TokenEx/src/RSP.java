import java.util.Scanner;
import java.util.Random;

public class RSP {
	public static void printWinner(String ps, String cps, String msg) {
		System.out.printf("철수[%s] : 컴퓨터[%s]\n", ps, cps);
		System.out.println(msg);
	}
	
	public static void run(int p, int cp) {
		String ps = p == 0 ? "가위" : p == 1 ? "바위" : "보"; 
		String cps = cp == 0 ? "가위" : cp == 1 ? "바위" : "보";
		String msg = null;
		
		if (p == cp) 
			msg = "비겼습니다.";
		else if ((p == 0 && cp == 1) || (p == 1 && cp == 2) || (p == 2 && cp == 0)) 
			msg = "컴퓨터가 이겼습니다.";
		else if ((p == 0 && cp == 2) || (p == 1 && cp == 0) || (p == 2 && cp == 1)) 
			msg = "철수가 이겼습니다.";
		
		printWinner(ps, cps, msg);
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		
		while (true) {
			System.out.print("철수 [가위(0), 바위(1), 보(2), 끝내기(3)] >> ");
			int picked = s.nextInt();
			int comPicked = r.nextInt(3);
			
			if (picked == 3) {
				System.out.println("프로그램 종료");
				s.close();
				return;
			}
			
			run(picked, comPicked);
		}
	}
}
