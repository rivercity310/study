<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-23
  Time: 오후 5:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="text-align:center">
  <%
    String msg = "오류 원인: " + exception;

    exception.printStackTrace();
  %>
</body>
</html>
