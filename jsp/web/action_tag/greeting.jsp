<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-24
  Time: 오후 4:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%
    request.setCharacterEncoding("utf-8");
    if (request.getParameter("country").equals("korea")) { %>
      <%= "감사합니다" %>
  <% } else if (request.getParameter("country").equals("english")) { %>
      <%= "Thank you" %>
  <% } else if (request.getParameter("country").equals("japan")) { %>
      <%= "ありがとう" %>
  <% } %>
</body>
</html>
