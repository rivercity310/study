package Generic;

import java.util.Scanner;
import java.util.Vector;
import java.util.InputMismatchException;

abstract class Shape {
	private int x, y;
	public Shape(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	abstract double getArea();
}

class Line extends Shape {
	public Line(int x, int y) {
		super(x, y);
	}
	
	public double getArea() {
		return 0;
	}
}

class Rect extends Shape {
	private int width, height;
	public Rect(int x, int y, int w, int h) {
		super(x, y);
		this.width = w;
		this.height = h;
	}
	
	public double getArea() {
		return this.width * this.height;
	}
}

class Circle extends Shape {
	private int radius;
	public Circle(int x, int y, int r) {
		super(x, y);
		this.radius = r;
	}
	
	public double getArea() {
		return Math.PI * Math.pow(radius, 2);
	}
}

public class TrainingEx10 {
	private Scanner s;
	private Vector<Shape> v;
	
	public TrainingEx10() {
		s = new Scanner(System.in);
		v = new Vector<Shape>();
	}
	
	private void printError(Exception e) {
		String msg = e.getClass().getSimpleName().equals("ArrayIndexOutOfBoundsException") ? "배열의 범위를 초과했습니다." : "선택지에 있는 정수를 입력해주세요.";
		System.out.println("\n**");
		System.out.println(e);
		System.out.println(msg);
		System.out.println("**\n");
	}
	
	
	private void insert() {
		System.out.print("Line(1), Rect(2), Circle(3) >> ");
		int p = s.nextInt();
		if (p == 1) v.add(new Line(0, 0));
		else if (p == 2) v.add(new Rect(0, 0, 1, 1));
		else if (p == 3) v.add(new Circle(0, 0, 1));
		else System.out.println("잘못된 선택입니다.");
	}
	
	private void delete() {
		System.out.print("삭제할 도형의 위치 >> ");
		int d = s.nextInt();
		if (v.size() >= 0 && d >= 0) v.remove(d - 1);
		else System.out.println("삭제할 수 없습니다.");
	}
	
	private void run() {
		System.out.println("그래픽 에디터 beauty를 실행합니다.");
		while(true) {
			try {
				System.out.print("삽입(1), 삭제(2), 모두 보기(3), 종료(4) >> ");
				int select = s.nextInt();
				switch(select) {
				case 1:
					insert();
					break;
				case 2:
					delete();
					break;
				case 3:
					for (int i = 0; i < v.size(); i++) {
						String r = v.get(i).getClass().getName().substring(8);
						System.out.print(r + " \n");
						System.out.printf("%s의 넓이: %.2f", r, v.get(i).getArea());
						System.out.println();
					}
					System.out.println();
					break;
				case 4:
					System.out.println("beauty를 종료합니다...");
					s.close();
					return;
				default:
					System.out.println("잘못된 선택입니다..");
					break;
				}
			} catch (Exception e) {
				printError(e);
				s.nextLine();
				continue;
			}
		}
	}
	
	public static void main(String[] args) {
		var app = new TrainingEx10();
		app.run();
	}
}
