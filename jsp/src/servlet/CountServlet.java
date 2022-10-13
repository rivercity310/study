package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CountServlet", value = "/CountServlet")
public class CountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        if (session.getAttribute("cnt") == null)
            session.setAttribute("cnt", new int[1]);

        int[] count = (int[])session.getAttribute("cnt");
        count[0]++;

        out.print("<h2>방문: " + count[0] + "회</h2>");
        out.close();
    }
}
