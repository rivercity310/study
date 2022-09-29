<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-29
  Time: 오후 7:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jspbean.CountVO" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h2>session scope 객체 공유</h2>
  <hr/>

  <%
    CountVO vo = (CountVO)request.getAttribute("sessionobj");
  %>

  <h3>추출된 카운트 값 : <%= vo.getNumber() %></h3>
  <hr/>

  <jsp:useBean id="sessionobj" class="jspbean.CountVO" scope="session"/>
  <h3>추출된 카운트 값 (액션 태그) : <jsp:getProperty name="sessionobj" property="number"/></h3>
  <hr/>

  <h3>추출된 카운트 값 (EL) : ${sessionScope.sessionobj.number}</h3>
</body>
</html>
