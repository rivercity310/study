package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FlowServlet
 */
@WebServlet("/FlowServlet")
public class FlowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public FlowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Servlet ��ü�� ������ ���� ȣ��Ǵ� �޼���, ���� 1ȸ�� ȣ���
    public void init(ServletConfig config) throws ServletException {
    	System.out.println("init() ȣ��!");
    }
    
    // Servlet ��ü�� �޸𸮿��� ������ �� ȣ��Ǵ� �޼���, ������ 1���� ȣ���
    public void destroy() {
    	System.out.println("destroy() ȣ��!!");
    }
    
    // ��û ��Ŀ� ������� �׻� ȣ��Ǵ� �޼���, ��û ��İ� ������ ����� ó���Ϸ��� ��� �������̵�
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("service() ȣ��!");
    	
    }
}
