package InputOutput;

import java.io.*;

public class FileOutputStreamEx {
	public static void main(String[] args) {
		byte b[] = {7, 51, 3, 4, -1, 24};
		String filePath = "C:\\Users\\h9701\\Desktop\\FileOutputStream.txt"; 
		
		try {
			FileOutputStream fout = new FileOutputStream(filePath);
			fout.write(b);
			fout.close();
		} catch (IOException e) {
			System.out.println(e);
			System.out.println(filePath + "에 저장할 수 없습니다.");
			return;
		}
		
		System.out.println(filePath + "을 저장하였습니다.");
	}
}
