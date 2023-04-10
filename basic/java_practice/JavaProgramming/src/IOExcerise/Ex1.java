package IOExcerise;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;
import java.io.*;

class Person {
	private String name;
	private String number;
	
	public Person(String name, String number) {
		this.name = name;
		this.number = number;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getNumber() {
		return this.number;
	}
}

public class Ex1 {
	private Scanner s;
	private HashMap<String, Person> h;
	private FileWriter fout;
	private StringBuffer sb;
	private Random r;
	
	public Ex1() {
		s = new Scanner(System.in);
		sb = new StringBuffer();
		h = new HashMap<String, Person>();
		r = new Random();
	}
	
	private void trying(String path) {
		try {
			fout = new FileWriter(new File(path));
			var keys = h.keySet();
			var it = keys.iterator();
			
			while (it.hasNext()) {
				String key = it.next();
				Person p = h.get(key);
				
				String line = p.getName() + " " + p.getNumber() + "\r\n";
				fout.write(line, 0, line.length());
				fout.flush();
			}
			
			System.out.println(path + "에 저장하였습니다.");
			fout.close();
			s.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private String makeNewPath(String path) {
		int randomNumber = r.nextInt(10000);
		if (path == null) return "C:\\Temp\\phone" + randomNumber + ".txt";
		else {
			int newRandomNumber = r.nextInt(10000);
			while (true) {
				if (randomNumber == newRandomNumber) newRandomNumber = r.nextInt(10000);
				else break;
			}
			
			return "C:\\Temp\\phone" + newRandomNumber + ".txt";
		}
	}
	
	private void save() {
		String path = makeNewPath(null);
		File phoneText = new File(path);
		String newPath = makeNewPath(path);
		
		String lines = null;
		File[] v = phoneText.getParentFile().listFiles();
		for (File k : v) {
			sb.append(k.toString() + "\n");
			lines = sb.toString();
			if (lines.contains(path)) {
				System.out.println("파일 이름 중복!!");
			}
		}
		
		System.out.println("\n---------- File Lists -----------\n");
		System.out.println(lines);
		System.out.println("---------------------------------");
		
		trying(newPath);
	}
	
	public void run() {
		System.out.println("전화번호 입력 프로그램입니다.");
		while (true) {
			System.out.print("이름 전화번호 >> ");
			String name = s.next();
			if (name.equals("stop")) {
				save();
				System.out.print("프로그램을 종료합니다.");
				return;
			}
			String number = s.next();
			
			h.put(name, new Person(name, number));
		}
	}
	
	public static void main(String[] args) {
		var app = new Ex1();
		app.run();
	}
}
