package Socket;

import java.io.*;
import java.net.*;
import java.util.*;

public class CalcServerEx {
	public static String calc(String exp) {
		StringTokenizer st = new StringTokenizer(exp, " ");
		if (st.countTokens() != 3) return "error!";
		
		String res = null;
		int op1 = Integer.parseInt(st.nextToken());
		String operator = st.nextToken();
		int op2 = Integer.parseInt(st.nextToken());
		
		switch (operator) {
		case "+":
			res = Integer.toString(op1 + op2);
			break;
		case "-":
			res = Integer.toString(op1 - op2);
			break;
		case "*":
			res = Integer.toString(op1 * op2);
			break;
		default:
			res = "error";
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedWriter out = null;
		ServerSocket listener = null;
		Socket socket = null;
		
		try {
			listener = new ServerSocket(9999);
			System.out.println("연결 대기중.....");
			socket = listener.accept();
			System.out.println("연결되었습니다.");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while (true) {
				String inputMessage = in.readLine();
				if (inputMessage.equalsIgnoreCase("bye")) {     // IgnoreCase: 대소문자 구분X
					System.out.println("클라이언트에서 연결을 종료하였음");
					break;
				}
				
				System.out.println(inputMessage);
				String res = calc(inputMessage);
				out.write(res + "\n");
				out.flush();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (socket != null) socket.close();
				if (listener != null) listener.close();
			} catch (IOException e) {
				System.out.println("클라이언트와 채팅 중 오류 발생!");
			}
		}
	}
}











