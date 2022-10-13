<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-24
  Time: 오후 5:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <jsp:useBean id="today" scope="application" class="jspbean.Today"/>
  <h3>
    <jsp:getProperty name="today" property="year"/>년
    <jsp:getProperty name="today" property="month"/>월
    <jsp:getProperty name="today" property="date"/>일
  </h3>
</body>
</html>
