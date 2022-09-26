<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-26
  Time: 오후 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="application/json; charset=UTF-8" language="java" %>
<%
  if (request.getParameter("id").equals("ajaxtest") && request.getParameter("passwd").equals("12345")) {
    %>
{
  "result" : "ok"
}
<%
  } else {
%>
{
  "result" : "fail"
}
<%
  }
%>