package InputOutput;

import java.io.*;

public class FileReadHangulSuccess {
	public static void main(String[] args) {
		char c[] = new char[20];
		try {
			var fin = new FileInputStream("C:\\Users\\h9701\\Desktop\\hangul.txt");
			var in = new InputStreamReader(fin, "UTF-8");
			
			System.out.println("인코딩 문자 집합은 " + in.getEncoding());
			int length = in.read(c);
			
			for (char k : c) System.out.print(k);
			System.out.println("\n파일의 길이는: " + length);
			in.close();
			fin.close();
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
