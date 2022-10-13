package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/getHTML", "/getXML", "/getJSON", "/getImage"})
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResponseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String filename = "C:/Users/h9701/Desktop/eclipse-ent/test/";
		String contentType = "";
		
		if (uri.endsWith("getHTML")) {
			filename = filename.concat("test.html");
			contentType = "text/html; charset=utf-8";
		}
		else if (uri.endsWith("getXML")) {
			filename = filename.concat("test.xml");
			contentType = "application/xml; charset=utf-8";
		}
		else if (uri.endsWith("getJSON")) {
			filename += "test.json";
			contentType = "application/json; charset=utf-8";
		} 
		else if (uri.endsWith("getImage")) {
			filename += "test.png";
			contentType = "image/png";
		}
		
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		response.setContentType(contentType);
		
		if (contentType.startsWith("image")) {
			byte[] content = new byte[(int)f.length()];
			ServletOutputStream sos = response.getOutputStream();
			
			fis.read(content);
			sos.write(content);
			sos.close();
		}
		else {
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			PrintWriter out = response.getWriter();
			
			String line = null;
			while ((line = br.readLine()) != null)
				out.println(line);
			
			out.close();
			br.close();
			isr.close();
		}
		
		fis.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
