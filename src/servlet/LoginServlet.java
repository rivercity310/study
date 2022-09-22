package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginSvlt")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("served at: ").append(request.getContextPath());
		
		response.setContentType("text/html; charset=utf-8");
		String uid = request.getParameter("id");
		String upw = request.getParameter("pw");
		
		PrintWriter writer = response.getWriter();
		
		String str = "<html><h1>";
		str += "User ID : " + uid + "<br/>";
		str += "User PW : " + upw;
		str += "</h1></html>";
		
		writer.append(str);
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
