package InputOutput;

import java.io.*;

public class FileInputStreamEx {
	public static void main(String[] args) {
		String filePath = "C:\\Users\\h9701\\Desktop\\FileOutputStream.txt"; 
		byte b[] = new byte[6];
		
		try {
			FileInputStream fin = new FileInputStream(filePath);
			fin.read(b);
			
			System.out.println("바이너리 파일을 읽어들린 후 출력합니다.");
			for (byte k : b) System.out.print(k + " ");
			
			fin.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
