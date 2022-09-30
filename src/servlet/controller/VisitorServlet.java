package servlet.controller;

import dao.VisitorDAO;
import jspbean.VisitorVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "VisitorServlet", value = "/VisitorServlet")
public class VisitorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedView = "mvc/view/visitorView1.jsp";

        VisitorDAO dao = new VisitorDAO();
        ArrayList<VisitorVO> list = dao.listAll();
        if (list != null && list.size() > 0)
            request.setAttribute("list", list);
        else
            request.setAttribute("msg", "방명록에 저장된 글이 없어요...");

        request.getRequestDispatcher(selectedView).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String insertedView = "mvc/view/visitorView1.jsp";

        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String content = request.getParameter("content");

        VisitorVO vo = new VisitorVO();
        vo.setName(name);
        vo.setContent(content);

        VisitorDAO dao = new VisitorDAO();
        boolean result = dao.insert(vo);

        if (result)
            request.setAttribute("msg", name + "님의 글이 성공적으로 저장되었습니다!");
        else
            request.setAttribute("msg", name + "님의 글 저장에 실패하였습니다!");

        request.getRequestDispatcher(insertedView).forward(request, response);
    }
}
