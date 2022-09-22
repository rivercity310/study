package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UploadServlet")
@MultipartConfig (
		location = "C:\\Users\\h9701\\Desktop\\eclipse-ent\\test\\uploaded",
		maxFileSize = 1024 * 1024 * 5,
		maxRequestSize = 1024 * 1024 * 5 * 5
		)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String path = "C:/Users/h9701/Desktop/eclipse-ent/test/uploaded";
		File isDir = new File(path);
		if (!isDir.isDirectory())
			isDir.mkdirs();
		
		Collection<Part> parts = request.getParts();
		for (Part p : parts) {
			if (p.getContentType() != null) {
				String filename = p.getSubmittedFileName();
				
				if (filename != null) {
					p.write(filename.substring(0, filename.lastIndexOf("."))
							+ "_" + System.currentTimeMillis() + filename.substring(filename.lastIndexOf(".")));
					
					out.print("<br/>���ε��� ���� �̸� : " + filename);
					out.print("<br/>ũ�� : " + p.getSize());
				}
			}
			else {
				String partName = p.getName();
				String fieldValue = request.getParameter("partName");
				out.print("<br/>" + partName + ": " + fieldValue);
			}
		}
		
		out.close();
	}

}
