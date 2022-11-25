import java.util.Scanner;
import java.util.Random;

class Person {
	private String name;
	
	public Person(String n) {
		this.name = n;
	}
	
	public String getName() {
		return this.name;
	}
}

public class GamblingEx {
	private static Person[] person;
	private static Scanner s;
	private static Random r;
	private static int count = 0;
	
	private static void initGame() {
		s = new Scanner(System.in);		
		System.out.print("게임에 참여할 선수 숫자: ");
		int size = s.nextInt();
		person = new Person[size];
		
		for (int i = 0; i < person.length; i++) {
			System.out.printf("%d번째 선수 이름: ", i + 1);
			String name = s.next();
			person[i] = new Person(name);
		}
		
		run();
	}
	
	private static void run() {
		r = new Random();
		int i = 0;
		while (true) {
			if (i >= person.length) i = 0;
			System.out.printf("[%s]: <Enter> ", person[i].getName());
			s.nextLine();
			
			int first = r.nextInt(45) + 1;
			int second = r.nextInt(45) + 1;
			int third = r.nextInt(45) + 1;			
			System.out.printf("%d %d %d\t", first, second, third);
			
			if (first == second && second == third) {
				System.out.printf("%s님이 승리하였습니다.\n", person[i].getName());
				s.close();
				System.out.printf("시행 횟수: %d", count);
				return;
			} else {
				System.out.print("아쉽군요!\n");
			}
			
			count++;
			i++;
		}
	}
	
	public static void main(String[] args) {
		initGame();
	}
}
