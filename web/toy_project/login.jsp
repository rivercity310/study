<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-23
  Time: 오후 7:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        if (id.equals("tmdtn97") && pw.equals("a12345")) {
            if (session.isNew() || session.getAttribute("member_id") == null) {
                session.setAttribute("member_id", id);
                session.setAttribute("member_pw", pw);
                session.setMaxInactiveInterval(60 * 30);
            }
    %>
            <%@ include file="success.jspf" %>
         <%
        }
        %>



</body>
</html>
