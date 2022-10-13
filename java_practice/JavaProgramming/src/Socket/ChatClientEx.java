package Socket;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientEx {
	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(System.in);
			Socket sock = new Socket("localhost", 9999);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintWriter out = new PrintWriter(sock.getOutputStream());
			
			while (true) {
				System.out.print("Client >> ");
				String mymsg = scan.nextLine();
				out.println(mymsg);
				out.flush();
				
				String msg = in.readLine();
				System.out.println(msg);
				if (mymsg.equals("bye")) break;
			}
			
			in.close();
			out.close();
			scan.close();
			sock.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
