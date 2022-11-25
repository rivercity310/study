package InputOutput;

import java.util.Scanner;
import java.io.*;

public class BufferedIOEx {
	public static void main(String[] args) {
		try {
			var s = new Scanner(System.in);
			var fin = new FileReader("C:\\Users\\h9701\\Desktop\\test.txt");
			//BufferedOutputStream out = new BufferedOutputStream(System.out, 5);
			
			// 한글을 출력하려면 out을 다음과 같이 수정해야 한다.
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out), 5);
			
			int c;
			while ((c = fin.read()) != -1)
				out.write(c);
			
			out.flush();	// 버퍼에 남아 있던 문자 모두 출력
			
			s.close();
			fin.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
