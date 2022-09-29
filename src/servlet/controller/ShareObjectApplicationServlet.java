package servlet.controller;

import jspbean.CountVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShareObjectApplicationServlet", value = "/ShareObjectApplicationServlet")
public class ShareObjectApplicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        context.setAttribute("applicationobj", new CountVO());

        CountVO vo = (CountVO)context.getAttribute("applicationobj");
        vo.setNumber(30);

        response.sendRedirect("mvc/view/applicationView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
