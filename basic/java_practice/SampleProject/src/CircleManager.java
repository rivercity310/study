import java.util.Scanner;
import java.util.InputMismatchException;

public class CircleManager {
	private static Circle[] setCircleInfo() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("배열 몇개를 생성할까요? ");
		int length = scanner.nextInt();
		Circle c[] = new Circle[length];
		
		for (int i = 0; i < c.length; i++) {
			System.out.print("x, y, radius를 순서대로 입력하시오: ");
			try {
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				int radius = scanner.nextInt();
				c[i] = new Circle(x, y, radius);
			} catch (InputMismatchException e) {
				System.out.println("잘못된 입력입니다! 정수로 입력하세요.");
				scanner.nextLine();
				i--;
				continue;
			} 
		}
		
		scanner.close();
		
		return c;
	}
	
	private static void printCircleInfo(Circle c[]) {
		System.out.println("\n\n객체 배열 안에 있는 객체 정보를 출력합니다.");
		for (int i = 0; i < c.length; i++) {
			System.out.printf("c[%d] >> ", i);
			c[i].show();
		}
		
		System.out.print("가장 큰 원의 넓이는: ");
		double maxCircle = c[0].getArea();
		for (int i = 0; i < c.length; i++) {
			if (maxCircle < c[i].getArea()) 
				maxCircle = c[i].getArea();
		}
		System.out.printf("%.2f", maxCircle);
	}
	
	public static void main(String[] args) {
		Circle c[] = setCircleInfo();
		printCircleInfo(c);
		
	}
}


class Circle {
	private int x, y, radius;
	
	public Circle(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public void show() {
		System.out.printf("좌표: (%d, %d), 반지름: %d\n", x, y, radius);
	}
	
	public double getArea() {
		return Math.pow(this.radius, 2) * Math.PI; 
	}
}