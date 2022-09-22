package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/queryget", "/querypost"})
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QueryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("guestName");
		int unumb = Integer.parseInt(request.getParameter("num"));
		
		String line = "<h2>��û ��� : " + request.getMethod() + "</h2>";
		line += "<h2>��û  URI : " + request.getRequestURI() + "</h2>";
		line += "<h2>����� �̸��� : " + uname + "�̱���!</h2>";
		line += "<h2>����� �����ϴ� ���ڴ� : " + unumb + "�̱���!</h2>";
		line += "<a href='" + request.getHeader("referer") + "'> �Է� ȭ������ ����</a>";
		
		out.print(line);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post ������� ���� ���ڿ� ���� �� ���ڿ� ���� ����
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
