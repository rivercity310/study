package Generic;

import java.util.Scanner;
import java.util.HashMap;

class Student {
	private String major;
	private String stID;
	private double gradeAve;
	
	public Student(String m, String s, double g) {
		this.major = m;
		this.stID = s;
		this.gradeAve = g;
	}
	
	
	public String toString() {
		String res = "학과: " + this.major + "\n";
		res += "학번: " + this.stID + "\n";
		res += "학점평균: " + this.gradeAve;
		
		return res;
	}
}

public class TrainingEx5 {
	private void run() {
		var h = new HashMap<String, Student>();
		var s = new Scanner(System.in);
		
		System.out.println("학생 이름, 학과, 학번, 학점평균을 입력하세요.");
		for (int i = 0; i < 4; i++) {
			System.out.print(">> ");
			String name = s.next();
			String major = s.next();
			String stID = s.next();
			double ga = s.nextDouble();
			
			h.put(name, new Student(major, stID, ga));
		}
		System.out.println("--------------------------------");
		
		var keys = h.keySet();
		var it = keys.iterator();
		while (it.hasNext()) {
			String name = it.next();
			System.out.println("이름: " + name);
			System.out.println(h.get(name).toString());
			System.out.println("--------------------------------");
		}
		
		var keyAry = keys.toArray();
		while (true) {
			System.out.print("학생 이름: ");
			String search = s.next();
			if (search.equals("stop")) break;
			
			String st = null;
			for (int i = 0; i < keyAry.length; i++) {
				String n = (String)keyAry[i]; 
				if (search.equals(n)) st = h.get(n).toString();
			}
			
			if (st != null) System.out.println(st);
			else System.out.println("등록되지 않은 학생입니다.");
		}
	}
	
	public static void main(String[] args) {
		var app = new TrainingEx5();
		app.run();
	}
}
