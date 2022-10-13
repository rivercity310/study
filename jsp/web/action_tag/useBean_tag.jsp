<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-24
  Time: 오후 5:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <jsp:useBean id="cnt" class="jspbean.CountVO" scope="session" />
  ${ cnt.getNumber() } <br/>
  <% cnt.setNumber(10); %> <br/>
  <%= cnt.getNumber() %> <br/>
  <% cnt.setNumber(20); %> <br/>
  <%= cnt.getNumber() %> <br/>
  ${ cnt.getNumber() }
</body>
</html>
