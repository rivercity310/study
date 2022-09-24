<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-24
  Time: 오후 4:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>유저 페이지입니다.</h1>
  <h3>전달된 파라미터 : <span style="color:red"><%= request.getParameter("message")%></span></h3>
</body>
</html>
