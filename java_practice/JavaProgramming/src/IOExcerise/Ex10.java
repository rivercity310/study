package IOExcerise;

import java.util.Scanner;
import java.io.*;

public class Ex10 {
	private StringBuffer sb;
	private Scanner s;
	
	public Ex10() {
		sb = new StringBuffer();
		s = new Scanner(System.in);
	}
	
	private String getPath(String path) {
		String[] pref = {"C:\\", "c:\\", "c:/", "C:/"};
		String defaultPath = "C:\\Users\\h9701\\eclipse-workspace\\JavaProgramming\\src\\IOExcerise\\";

		boolean StartedWithPref = false;
		for (String k : pref) 
			if (path.startsWith(k))
				StartedWithPref = true;
		
		return StartedWithPref ? path : defaultPath.concat(path);
	}
	
	private boolean readFile() {
		System.out.print("대상 파일명 입력: ");
		String path = getPath(s.next());
		System.out.printf("%s로 검색합니다...\n", path);
		
		try {
			Scanner sf = new Scanner(new FileInputStream(path));
			while (sf.hasNext())
				sb.append(sf.nextLine() + "\n");
			
			// 남아있는 버퍼 제거
			s.nextLine();
			sf.close();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
		return true;
	}
	
	private void run() {
		while (true) {
			System.out.print("검색할 단어나 문장: ");
			String input = s.nextLine();
			if (input.equals("그만")) {
				s.close();
				System.out.println("프로그램을 종료합니다.");
				return;
			}
			
			String[] strAry = sb.toString().split("\n");
			for (String k : strAry) 
				if (k.contains(input))
					System.out.println(k);
			
		}
	}
	
	public static void main(String[] args) {
		var app = new Ex10();
		if (app.readFile()) app.run();
	}
}
