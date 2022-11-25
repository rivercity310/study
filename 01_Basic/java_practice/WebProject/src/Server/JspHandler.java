package Server;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import http.JspService;

/*
 * setUser: 세션에 입력 파라미터 저장
 * getUser: 세션에 저장된 파라미터 출력
 * 
 */

public class JspHandler {
	private String getHtml(String desc) {
		return "<html><meta charset='utf-8'>" + desc + "</html>";
	}
	
	private String getMsg(int code, String html) {
		String msg = "HTTP/1.1" + code + "\n";
		msg += "Content-Type: text/html;charset=utf-8\n";
		msg += "Content-Language: ko\n";
		msg += "Date: " + new java.util.Date().toString() + "\n";
		msg += "\n";
		
		msg += html;
		return msg;
	}
	
	private String getClassName(String file) {
		int idx = file.lastIndexOf(".jsp");
		return idx < 0 ? "" : file.substring(0, idx).replaceAll("/", ".");
	}
	
	private void sendText(OutputStream os, String msg) throws UnsupportedEncodingException {
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, "utf-8"));
		pw.println(msg);
		pw.flush();
	}
	
	/*
	 * 1. Class.forName(String path): path에 위치한 클래스를 찾아 메모리로 로드
	 *   - path에 클래스 경로가 들어가야 함 ("jsp/SetUser.class" 파일을 로드하려면 "jsp.SetUser")
	 *   
	 * 2. Class.newInstance(): 주어진 클래스에 대한 객체 생성 후 전달
	 *   - 이 메소드는 Object 형태로 객체를 리턴하므로, 명시적으로 타입 캐스팅을 해주어야 한다
	 */
	public void send(OutputStream os, String host, String file, String params[]) throws UnsupportedEncodingException {
		file = "jsp" + getClassName(file);
		
		try {
			Class c = Class.forName(file);
			JspService svc = (JspService)c.newInstance();
			sendText(os, getMsg(200, svc.getHtml(Session.getInstance(), host, params)));
		} catch (Exception ex) {
			ex.printStackTrace();
			sendText(os, getMsg(500, getHtml("서버 오류가 발생하였습니다.")));
		}
		
	}

	
}
