package servlet.controller;

import jspbean.CountVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShareObjectRequestServlet", value = "/ShareObjectRequestServlet")
public class ShareObjectRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CountVO vo = new CountVO();
        vo.setNumber(10);

        request.setAttribute("requestobj", vo);
        request.getRequestDispatcher("mvc/view/requestView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
