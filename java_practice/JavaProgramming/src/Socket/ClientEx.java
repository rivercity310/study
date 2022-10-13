package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientEx {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 9999);
			System.out.println("Client on....");
			
			// readLine() 함수 이용을 위해, BufferedReader 클래스 이용
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			
			String[] msgs = {"hello", "aloha", "bye"};
			for (int i = 0; i < msgs.length; i++) {
				// 서버로 메세지 전송
				out.println(msgs[i]);
				out.flush();         					// 출력 스트림 버퍼를 비움 -> 서버로 전송
				
				// 메세지 수신
				String msg = in.readLine();
				System.out.println(msg);
			}
			
			in.close();
			out.close();
			socket.close();
			System.out.println("Client Stopped");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
