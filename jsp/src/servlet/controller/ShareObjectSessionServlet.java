package servlet.controller;

import jspbean.CountVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShareObjectSessionServlet", value = "/ShareObjectSessionServlet")
public class ShareObjectSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("sessionobj") == null)
            session.setAttribute("sessionobj", new CountVO());

        CountVO vo = (CountVO)session.getAttribute("sessionobj");
        vo.setNumber(20);

        request.getRequestDispatcher("mvc/view/sessionView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
