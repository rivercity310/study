<%@ page import="jspbean.CountVO" %><%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-29
  Time: 오후 7:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h2>request scope 객체 공유</h2>
  <hr/>

  <%
    CountVO vo = (CountVO)request.getAttribute("requestobj");
  %>

  <h3>추출된 카운트 값 (스크립트 릿) : <%= vo.getNumber() %></h3>
  <hr/>

  <jsp:useBean id="requestobj" class="jspbean.CountVO" scope="request"/>
  <h3>추출된 카운트 값 (액션 태그) : <jsp:getProperty name="requestobj" property="number"/></h3>
  <hr/>

  <h3>추출된 카운트 값 (EL) : ${requestScope.requestobj.number}</h3>
</body>
</html>
