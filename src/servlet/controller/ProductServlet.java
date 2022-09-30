package servlet.controller;

import jspbean.ProductVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pid = request.getParameter("pid");

        if (session.getAttribute("countnum") == null)
            session.setAttribute("countnum", new ProductVO());

        ProductVO pvo = (ProductVO)session.getAttribute("countnum");
        if (pid.equals("001")) pvo.setApple(1);
        else if (pid.equals("002")) pvo.setBanana(1);
        else if (pid.equals("003")) pvo.setHallabong(1);
        else session.removeAttribute("countnum");

        request.getRequestDispatcher("mvc/view/productView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
