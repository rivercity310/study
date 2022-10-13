package Server;
import java.io.*;
import java.net.*;


class ServerThread implements Runnable {
	private Socket sock;
	
	public ServerThread(Socket sock) {
		this.sock = sock;
	}
	
	@Override
	public void run() {
		synchronized(this) {
			try {
				HttpRequest req = new HttpRequest();
				String msg = req.receive(sock.getInputStream());
				
				String url = req.getUrl(msg);		
				System.out.println(url);
				
				if (url != null) {
					String host = req.getHost(msg);
					String file = req.getFile(url);
					String params[] = req.getParams(url);
					
					System.out.println("Host: " + host);
					System.out.println("File: " + file);
					if (params != null)
						for (int i = 0; i < params.length; i++) 
							System.out.printf("params[%d]: %s\n", i, params[i]);
				
						
					HttpResponse res = new HttpResponse();
					res.send(sock.getOutputStream(), host, file, params);
				}
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}

public class WebServer {
	public static void main(String[] args) throws InterruptedException {
		try {
			@SuppressWarnings("resource")
			ServerSocket srvsock = new ServerSocket(80);
			System.out.println("Server Started");
						
			while (true) {
				Socket sock = srvsock.accept();
				Thread thread = new Thread(new ServerThread(sock));
				thread.start();
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
