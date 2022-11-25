package InputOutput;

import java.util.*;
import java.io.*;

public class FileWriterEx {
	public static void main(String[] args) {
		try {
			var s = new Scanner(System.in);
			var fout = new FileWriter("C:\\Users\\h9701\\Desktop\\newTest.txt");
			
			while (true) {
				String line = s.nextLine();
				if (line.length() == 0) break;
				
				fout.write(line, 0, line.length());
				fout.write("\r\n", 0, 2);
			}
			
			fout.close();
			s.close();
			
		} catch (IOException e) {
			System.out.println(e);
		}
		
		
		try {
			FileInputStream fin = new FileInputStream("C:\\\\Users\\\\h9701\\\\Desktop\\\\newTest.txt");
			InputStreamReader in = new InputStreamReader(fin, "MS949");
			
			System.out.println("인코딩 문자 집합은 " + in.getEncoding());
			
			int c;
			while ((c = in.read()) != -1)
				System.out.print((char)c);
			
			in.close();
			fin.close();
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
