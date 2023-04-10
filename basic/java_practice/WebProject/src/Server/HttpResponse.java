package Server;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class HttpResponse {
	private String getMsgNotFound() {
		// Header
		String msg = "HTTP/1.1 404\n";
		msg += "Content-Type: text/html;charset=utf-8\n";
		msg += "Content-Language: ko\n";
		msg += "Date: " + new Date().toString() + "\n";   // toString 생략 가능
		msg += "\n";
		
		// Body
		msg += "<html><h1>404 Not Found</h1></html>";
		return msg;
	}
	
	private String getMsgText(String path) throws IOException {
		byte bytes[] = Files.readAllBytes(Paths.get(path));
		
		// Header
		String msg = "HTTP/1.1 200\n";
		msg += "Content-Type: text/html;charset=utf-8\n";
		msg += "Content-Language: ko\n";
		msg += "Content-Length: " + bytes.length + "\n";
		msg += "Date: " + new Date().toString() + "\n";   // toString 생략 가능 객체를 읽을 때 자동으로 toString() 메소드가 실행됨
		msg += "\n";
		
		// Body
		msg += new String(bytes, "utf-8");
		return msg;
	}
	
	private byte[] getMsgImage(String path) throws IOException {
		byte bytes[] = Files.readAllBytes(Paths.get(path));
		
		String msg = "HTTP/1.1 200\n";
		msg += "Content-Type: image/jpeg\n";
		msg += "Content-length" + bytes.length + "\n";
		msg += "\n";
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		bao.write(msg.getBytes());
		bao.write(bytes);
		
		return bao.toByteArray();
	}
	
	private void sendText(OutputStream os, String msg) throws UnsupportedEncodingException {
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, "utf-8"));
		pw.println(msg);
		pw.flush();
	}

	private void sendBytes(OutputStream os, byte bytes[]) throws IOException {
		os.write(bytes);
		os.flush();
	}
	
	// file로부터 확장자 분리
	private boolean isImage(String file) {
		String extentions[] = {"jpg", "jpeg", "png", "gif"};
		boolean flag = false;
		
		int idx = file.lastIndexOf(".");
		String ext = idx > 0 ? file.substring(idx + 1) : "";
		
		for (String k : extentions) {
			if (k.equals(ext)) {
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	
	private boolean isJsp(String file) {
		int idx = file.lastIndexOf(".");
		String ext = idx > 0 ? file.substring(idx + 1) : "";
		return ext.equals("jsp");
	}
	
	public void send(OutputStream os, String host, String file, String params[]) throws IOException {
		if (isJsp(file)) new JspHandler().send(os, host, file, params);
		else {
			// HTML 파일이 저장되는 루트 디렉토리를 web으로 지정
			// class 파일이 저장되는 bin 디렉토리 아래에 HTML 파일을 저장할 web 디렉토리 생성 
			// web/index.html
			file = "web" + file;    
			System.out.println("send file: " + file);
			
			//Session ses = Session.getInstance();
			//Object obj = ses.get("localhost");
			
			//System.out.println("Obj: " + obj.toString());
			
			String msg = null;
			boolean isExist = new File(file).exists();
			if (isExist) {
				if (isImage(file)) {
					byte bytes[] = getMsgImage(file);
					sendBytes(os, bytes);
				} else {
					msg = getMsgText(file);
					sendText(os, msg);
				}
			} else {
				msg = getMsgNotFound();
				sendText(os, msg);
			}
			
			System.out.println(msg + "\n\n\n");	
		}
		
		
	}
}
