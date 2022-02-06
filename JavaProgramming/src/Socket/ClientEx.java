package Socket;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientEx {
	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedWriter out = null;
		Socket socket = null;
		Scanner scanner = new Scanner(System.in);
		
		try {
			socket = new Socket("localhost", 9999);  // 클라이언트 소켓 생성, 서버에 연결
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while (true) {
				System.out.print("보내기 >> ");
				String outputMessage = scanner.nextLine();
				if (outputMessage.equals("bye")) {
					out.write(outputMessage + "\n");
					out.flush();
					break;
				}
				
				out.write(outputMessage + "\n");
				out.flush();
				
				String inputMessage = in.readLine();
				System.out.println("서버: " + inputMessage);
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				scanner.close();
				if (socket != null) socket.close();
			} catch (IOException e) {
				System.out.println("서버와 채팅 중 오류가 발생하였습니다.");
			}
		}
	}
}
