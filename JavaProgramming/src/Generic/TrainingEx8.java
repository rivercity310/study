package Generic;

import java.util.HashMap;
import java.util.Scanner;

class Point {
	private int point;
	public Point(int p) {
		this.point = p;
	}
	
	public void setPoint(int p) {
		this.point += p;
	}
	
	public int getPoint() {
		return this.point;
	}
}

public class TrainingEx8 {
	private void run() {
		var s = new Scanner(System.in);
		var h = new HashMap<String, Point>();
		
		System.out.println("** 포인트 관리 프로그램입니다 **");
		while (true) {
			System.out.print("이름과 포인트 입력: ");
			String name = s.next();
			if (name.equals("stop")) break;
			int point = s.nextInt();
			if (h.get(name) == null) h.put(name, new Point(point));
			else h.get(name).setPoint(point);
			
			var keys = h.keySet();
			var it = keys.iterator();
			while (it.hasNext()) {
				String n = it.next();
				System.out.printf("(%s, %d) ", n, h.get(n).getPoint());
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		var app = new TrainingEx8();
		app.run();
	}
}
