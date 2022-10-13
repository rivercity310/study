package servlet.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "eduServlet", value = "/eduServlet")
public class eduServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int scoreAvg = Integer.parseInt(request.getParameter("scoreavg"));
        String grd = "";

        if (scoreAvg >= 90) grd = "A";
        else if (scoreAvg >= 80) grd = "B";
        else grd = "C";

        String uri = "mvc/view/grade" + grd + ".jsp";
        request.getRequestDispatcher(uri).forward(request, response);
    }
}
