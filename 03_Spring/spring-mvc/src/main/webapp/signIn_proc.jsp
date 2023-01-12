<%@ page import="com.springbook.biz.mvc.user.UserVO" %>
<%@ page import="com.springbook.biz.mvc.user.impl.UserDAO" %>
<%
    request.setCharacterEncoding("UTF-8");

    String id = request.getParameter("id");
    String pw = request.getParameter("password");
    String name = request.getParameter("name");

    UserVO user = new UserVO();
    user.setId(id);
    user.setPassword(pw);
    user.setName(name);

    UserDAO userDAO = new UserDAO();
    userDAO.insertUser(user);

    response.sendRedirect("login.jsp");
%>