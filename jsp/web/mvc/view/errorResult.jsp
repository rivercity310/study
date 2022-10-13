<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-30
  Time: 오후 3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h2>오류 발생</h2>
  <hr/>
  <h3>원인 : <span>${requestScope.msg}</span></h3>
  <a href="mvc/calcForm.html">돌아가기</a>
</body>
</html>
