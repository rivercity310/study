package IOExcerise;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

// Ex1에서 정의한 Person 객체 사용
public class Ex8 {
	private Vector<Person> v;
	private Scanner s;

	public Ex8() {
		s = new Scanner(System.in);
		v = new Vector<Person>();
	}
	
	private void run() {
		String path = "C:\\Temp\\phone8079.txt";
		File f = new File(path);
		
		try {
			Scanner sf = new Scanner(new FileReader(f));
			while (sf.hasNext()) {
				String[] info = sf.nextLine().split(" ");
				v.add(new Person(info[0], info[1]));
			}
			sf.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.printf("총 %d개의 전화번호를 읽었습니다.\n", v.size());
		while (true) {
			String res = null;

			System.out.print("이름: ");
			String name = s.next();
			if (name.equals("stop")) {
				System.out.println("프로그램을 종료합니다.");
				s.close();
				break;
			}
			
			for (int i = 0; i < v.size(); i++) {
				Person obj = v.get(i);
				if (name.equals(obj.getName())) 
					res = obj.getNumber();
			}
			
			if (res == null) System.out.println("찾는 이름이 없습니다.");
			else System.out.println(res);
		}
	}
	
	public static void main(String[] args) {
		var app = new Ex8();
		app.run();
	}
}
