package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MemberLocalServlet")
public class MemberLocalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// ��� �������� ��û�ϵ� ������ Servlet ��ü�� Thread�� ���� ����ȴ�
	int member_v = 0;
	
    public MemberLocalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		int local_v = 0;
		
		member_v++; local_v++;
		
		out.print("<h2>member_v(�������) : " + member_v + "</h2>");
		out.print("<h2>local_v(��������) : " + local_v + "</h2>");
		out.print("hi");
		out.close();
	}
}
