package servlet.controller;

import jspbean.EncDecService;
import jspbean.MemberVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "memberServlet", value = "/memberServlet")
public class memberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "mvc/view/memberView.jsp";
        request.setCharacterEncoding("utf-8");

        MemberVO mvo = new MemberVO();
        mvo.setName(request.getParameter("name"));
        mvo.setPhoneNum(request.getParameter("phonenumber"));
        mvo.setId(request.getParameter("id"));
        mvo.setPasswd(EncDecService.encrypt(request.getParameter("passwd")));

        request.setAttribute("membervo", mvo);
        request.getRequestDispatcher(url).forward(request, response);
    }
}
