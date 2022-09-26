package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet("UploadServlet")
@MultipartConfig(location = "C:\\Users\\h9701\\Desktop\\eclipse-ent\\work-space\\example\\web\\ajax\\uploaded",
    maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5
)
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        String path = "C:\\Users\\h9701\\Desktop\\eclipse-ent\\work-space\\example\\web\\ajax\\uploaded";
        File isDir = new File(path);
        if (!isDir.isDirectory()) isDir.mkdirs();

        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            if (part.getContentType() != null) {
                String fileName = part.getSubmittedFileName();

                if (fileName != null) {
                    part.write(fileName.substring(0, fileName.lastIndexOf("."))
                            + "_" + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf(".")));

                    out.print("<br/>업로드한 파일 이름 : " + fileName);
                    out.print("<br/>파일 크기 : " + part.getSize());
                }
                else {
                    String partName = part.getName();
                    String fieldValue = request.getParameter(partName);
                    out.print("<br/>" + partName + " : " + fieldValue);
                }
            }
        }

        out.close();
    }
}
