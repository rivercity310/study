package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RequestInfoServlet")
public class RequestInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RequestInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String contextPath = request.getContextPath();
    	String method = request.getMethod();
    	String protocol = request.getProtocol();
    	String remoteAddr = request.getRemoteHost();
    	String requestURI = request.getRequestURI();
    	String requestURL = new String(request.getRequestURL());
    	String serverName = request.getServerName();
    	String userAgent = request.getHeader("user-agent");
    	String referer = request.getHeader("referer");
    	
    	boolean result = Pattern.matches(".*[win16|win32|linux|win64|mac].*", userAgent.toLowerCase());
    	String clientMachine = result ? "PC" : "Mobile";
    	
    	String browser = "";
    	String os[] = {"Trident", "MSIE", "Opera", "Firefox", "Safari", "Edge", "Chrome"};
    	
    	for (String str : os) {
    		if (userAgent.indexOf(str) > 0)
    			browser = str;
    	}
    	
    	response.setContentType("text/html; charset=utf-8");
    	PrintWriter printer = response.getWriter();
    	
    	String msg = "<h3>��û ������ ���ؼ� ������ ����</h3>";
    	msg += "<ul>";
    	msg += "<li>��û ���ؽ�Ʈ �н� : " + contextPath + "</li>";
    	msg += "<li>��û ��� : " + method + "</li>";
    	msg += "<li>��û �������� : " + protocol + "</li>";
    	msg += "<li>��û Ŭ���̾�Ʈ �ּ� : " + remoteAddr + "</li>";
    	msg += "<li>��û URI : " + requestURI + "</li>";
    	msg += "<li>��û URL : " + requestURL + "</li>";
    	msg += "<li>��û ������ : " + serverName + "</li>";
    	msg += "<li>��û Ŭ���̾�Ʈ �ý��� ���� : " + clientMachine + "</li>";
    	msg += "<li>��û ������ ���� : " + browser + "</li>";
    	msg += "<li>�� �������� ��û�� �� ������ : " + referer + "</li>";
    	msg += "</ul>";
    	
    	printer.print(msg);
    	printer.close();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
