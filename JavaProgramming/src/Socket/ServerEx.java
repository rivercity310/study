package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEx {
	public static void main(String[] args) {
		try {
			ServerSocket srvk = new ServerSocket(9999);
			System.out.println("Server on...");
			
			Socket socket = srvk.accept();
			// 클라이언트로부터 접속 시 아래 코드가 실행됩니다.
			
			// readLine() 함수 이용을 위해, BufferedReader 클래스 이용
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			
			while (true) {
				String msg = in.readLine();
				System.out.println(msg);
				
				out.println("Server: Ack");
				out.flush();
				
				if (msg.equals("bye")) break;
			}

			in.close();
			out.close();
			socket.close();
			srvk.close();
			
			System.out.println("Server Stopped");
			
		} catch(IOException err) {
			err.printStackTrace();
		}
	}
}
