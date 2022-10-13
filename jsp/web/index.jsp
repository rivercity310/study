<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-22
  Time: 오후 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <h2>오늘 날짜 : <%= new Date() %></h2>
    <h2><%= java.time.LocalDate.now() %></h2>

    <hr/>

    <%
      String name = request.getParameter("name");
      java.time.LocalTime lt = java.time.LocalTime.now();
    %>

    <h3>hello <%= name == null ? "Guest" : name %></h3>
    <h3>방문 시간 : <%= lt.getHour() + "시 " + lt.getMinute() + "분 " + lt.getSecond() + "초"%></h3>

    <hr/>

    <h2>JSP의 EL식</h2>
    <h3>${param.color}</h3>
  </body>
</html>
