package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "HttpSessionServlet", value = "/HttpSessionServlet")
public class HttpSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String command = request.getParameter("comm");
        HttpSession session = request.getSession();

        String msg = "";
        long time = session.getCreationTime();
        String id = session.getId();

        if (command.equals("view")) {
            if (session.isNew()) msg = "세션 객체 생성 : ";
            else msg = "세션 객체 추출 : ";
            msg += "<br>id : " + id + " <br>time : " + new Date(time);
        }
        else if (command.equals("delete")) {
            session.invalidate();
            msg = id + " 세션 객체 삭제!";
        }
        else {
            msg = "요청시 Query 문자열로 view or delete";
        }

        out.print(msg);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }
}
