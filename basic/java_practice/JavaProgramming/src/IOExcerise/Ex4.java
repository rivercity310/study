package IOExcerise;

import java.io.*;
import java.util.Scanner;

public class Ex4 {
	
	private String checkPath(String path) {
		String[] prefix = {"C:\\", "c:\\", "C:/", "c:/"};
		if (!(path.startsWith(prefix[0]) || path.startsWith(prefix[1]) || path.startsWith(prefix[2]) || path.startsWith(prefix[3]))) {
			String defaultPath = "C:\\Temp\\";
			return defaultPath.concat(path);
		}
		
		return path;
	}
	
	private String checkFiles(String[] path) {
		StringBuffer[] read = {new StringBuffer(), new StringBuffer()};
		
		try {
			for (int i = 0; i < path.length; i++) {
				// 바이트 스트림 -> 문자 스트림으로 변환
				FileInputStream fin = new FileInputStream(path[i]);
				InputStreamReader in = new InputStreamReader(fin, "MS949");
				
				int c;
				while ((c = in.read()) != -1) {
					read[i].append((char)c);
				}
				
				in.close();
				fin.close();
			}
		} 
		catch (IOException e) {
			return e.getMessage();
		}
	
		boolean equal = read[0].toString().contentEquals(read[1]);
		String res = equal ? "파일이 같습니다" : "파일이 다릅니다";
		
		return res;
	}
	
	private void run() {
		Scanner s = new Scanner(System.in);
		
		System.out.print("첫번째 파일 경로: ");
		String path1 = checkPath(s.nextLine());
		System.out.print("두번째 파일 경로: ");
		String path2 = checkPath(s.nextLine());
		
		System.out.println(path1 + "와 " + path2 + "를 비교합니다.");
		System.out.println(checkFiles(new String[] {path1, path2}));
		
		s.close();
		return;
	}
	
	public static void main(String[] args) {
		var app = new Ex4();
		app.run();
	}
}
