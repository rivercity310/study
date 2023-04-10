package Socket;

import java.util.*;
import java.io.*;
import java.net.*;

public class ServerEx2 {
	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedWriter out = null;
		ServerSocket listener = null;
		Socket socket = null;
		Scanner scanner = new Scanner(System.in);
		
		try {
			listener = new ServerSocket(9999);
			System.out.println("연결 대기중....");
			socket = listener.accept();
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while (true) {
				String inputMessage = in.readLine();
				if (inputMessage.equals("bye")) {
					System.out.println("클라이언트에서 bye로 연결을 종료하였음");
					break;
				}
				
				System.out.println("클라이언트: " + inputMessage);
				System.out.print("보내기 >> ");
				String outputMessage = scanner.nextLine();
				out.write(outputMessage + "\n");
				out.flush();
			}
			
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				scanner.close();
				socket.close();
				listener.close();
			} catch (IOException e) {
				System.out.println("클라이언트와 채팅 중 오류가 발생하였습니다.");
			}
		}
	}
}
