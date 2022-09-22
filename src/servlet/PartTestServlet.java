package servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

// multipart/form-data ������ �����͸� �����ϱ� ���� Annotation
@WebServlet("/PartTestServlet")
@MultipartConfig
public class PartTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PartTestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Collection<Part> parts = request.getParts();
		System.out.println("===== ��û ���� =====");
		
		for (Part p : parts) {
			System.out.print("name : ");
			System.out.println(p.getName());
			
			System.out.println("[��� ����]");
			for (String headerName : p.getHeaderNames()) {
				System.out.print(headerName + " : ");
				System.out.println(p.getHeader(headerName));
			}
			
			System.out.print("size : ");
			System.out.println(p.getSize());
			System.out.println("=================");
		}		
	}

}
