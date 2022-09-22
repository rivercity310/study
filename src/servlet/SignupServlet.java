package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignupServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printer = response.getWriter();
		
		String uid = request.getParameter("id");
		String upw = request.getParameter("pw");
		String upw_check = request.getParameter("pw_check");
		String uname = request.getParameter("name");
		
		String str = "<html>";
		
		if (!upw.equals(upw_check)) {
			str += "<h1>��й�ȣ ����ġ</h1>";
		} else {
			str += "<h1>User ID : " + uid + "</h1><br/>";
			str += "<h1>User PW : " + upw + "</h1><br/>";
			str += "<h1>User Name : " + uname + "</h1><br/>";
		}
		
		str += "</html>";
		
		printer.append(str);
		printer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
