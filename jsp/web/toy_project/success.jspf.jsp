<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-23
  Time: 오후 7:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <script>
    alert("로그인 성공!");
  </script>

  <h1>현재 시간 : <%= new Date() %></h1>
  <%
    String id = (String)session.getAttribute("member_id");
    String pw = (String)session.getAttribute("member_pw");
  %>
  <h1>ID : <%= id %></h1>
  <h1>PW : <%= pw %></h1>
  <a href="index.html">로그아웃</a>
</body>
</html>
