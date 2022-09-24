<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-24
  Time: 오후 4:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%
    String t = request.getParameter("type");
    if (t == null || t.equals("admin")) {
   %>
      <jsp:forward page="adminPage.jsp">
        <jsp:param name="message" value="Hi! Administrator" />
      </jsp:forward>
  <% } else if (t.equals("user")) { %>
      <jsp:forward page="userPage.jsp">
        <jsp:param name="message" value="Hi! User" />
      </jsp:forward>
  <% } %>
</body>
</html>
