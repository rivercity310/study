import java.util.Scanner;

class Seats {
	private String name;
	public Seats() {
		this.name = "  ---  ";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}

public class Reservation {
	private static Scanner scanner = new Scanner(System.in);
	final private static int AllSeats = 10;
	private static Seats[] S = new Seats[AllSeats];
	private static Seats[] A = new Seats[AllSeats];
	private static Seats[] B = new Seats[AllSeats];
	
	private static void initSeats() {
		for (int i = 0; i < AllSeats; i++) {
			S[i] = new Seats();
			A[i] = new Seats();
			B[i] = new Seats();
		}
	}
	
	private static void seatsReserve(Seats[] SEATS) {
		for (int i = 0; i < SEATS.length; i++) {
			System.out.print(SEATS[i].getName());
		}
		System.out.println();
		System.out.print("이름 >> ");
		String name = scanner.next();
		System.out.print("번호 >> ");
		int seats = scanner.nextInt();
		SEATS[seats - 1].setName(name);
	}
	
	private static void reserve() {
		System.out.print("좌석구분: S(1), A(2), B(3) >> ");
		int seatNum = scanner.nextInt();
		switch(seatNum) {
		case 1:
			System.out.print("S>> ");
			seatsReserve(S);
			break;
		case 2:
			System.out.print("A>> ");
			seatsReserve(A);
			break;
		case 3:
			System.out.print("B>> ");
			seatsReserve(B);
			break;
		default:
			System.out.println("잘못된 선택입니다.");
			break;
		}
	}
	
	private static void viewSeats(Seats[] SEATS) {
		for (int i = 0; i < SEATS.length; i++) {
			System.out.print(SEATS[i].getName());
		}
		System.out.println();
	}
	
	private static void view() {
		System.out.print("S >> ");
		viewSeats(S);
		System.out.print("A >> ");
		viewSeats(A);
		System.out.print("B >> ");
		viewSeats(B);
		System.out.println("<< 조회를 완료하였습니다. >>");
	}
	
	
	private static void cancleSeats(Seats[] SEATS) {
		for (int i = 0; i < SEATS.length; i++) {
			System.out.print(SEATS[i].getName());
		}
		System.out.println();
		System.out.print("이름 >> ");
		String name = scanner.next();
		for (int i = 0; i < SEATS.length; i++) {
			if (SEATS[i].getName().equals(name))
				SEATS[i].setName("  ---  ");
		}
	}
	
	
	private static void cancle() {
		System.out.print("좌석: S:1, A:2, B:3 >>");
		int select = scanner.nextInt();
		switch(select) {
		case 1:
			System.out.print("S >>");
			cancleSeats(S);
			break;
		case 2:
			System.out.print("A >>");
			cancleSeats(A);
			break;
		case 3:
			System.out.print("B >>");
			cancleSeats(B);
			break;
		default:
			System.out.println("잘못된 선택입니다.");
			break;
		}
	}
	
	private static void exit() {
		System.out.print("프로그램을 종료합니다...");
		scanner.close();
	}
	
	private static void run() {
		while (true) {	
			System.out.println("명품콘서트홀 예약 시스템입니다.");
			System.out.print("예약:1, 조회:2, 취소:3, 끝내기:4 >> ");
			int selected = scanner.nextInt();
			
			switch(selected) {
			case 1:
				reserve();
				break;
			case 2:
				view();
				break;
			case 3:
				cancle();
				break;
			case 4:
				exit();
				return;
			default:
				System.out.println("잘못된 선택입니다.");
				break;
			}
		}
	}
	
	
	public static void main(String[] args) {
		initSeats();
		run();
	}
}
