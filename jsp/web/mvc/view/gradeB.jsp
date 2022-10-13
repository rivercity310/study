<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-30
  Time: 오후 2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h2><%= request.getParameter("name") %>님은 B 등급입니다.</h2>
  <a href="mvc/eduForm.html">돌아가기</a>
</body>
</html>
