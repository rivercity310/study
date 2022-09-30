package servlet.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CalcServlet", value = "/CalcServlet")
public class CalcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));
        String op = request.getParameter("op");
        String url = "mvc/view/calcResult.jsp";

        int result = 0;
        if (op.equals("plus")) result = num1 + num2;
        else if (op.equals("minus")) result = num1 - num2;
        else if (op.equals("multiply")) result = num1 * num2;
        else {
            if (num2 == 0) {
                request.setAttribute("msg", "나눗셈 연산 시 두 번째 숫자는 0일 수 없습니다!");
                url = "mvc/view/errorResult.jsp";
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            else {
                result = num1 / num2;
            }
        }

        request.setAttribute("result", result);
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
