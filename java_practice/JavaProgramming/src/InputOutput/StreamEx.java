package InputOutput;

import java.io.InputStreamReader;
import java.io.IOException;

public class StreamEx {
	public static void main(String[] args) {
		InputStreamReader rd = new InputStreamReader(System.in);
		// rd.read()를 할 때는 예외 처리를 해주어야 함
		// 1. try-catch로 IOException 처리
		try {
			int c = rd.read();  	// 아스키 코드로 읽어들임
			System.out.println(c);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
