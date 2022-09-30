<%@ page import="jspbean.VisitorVO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-30
  Time: 오후 6:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h2>방명록 리스트</h2>
  <hr/>

  <%
    ArrayList<VisitorVO> list = (ArrayList<VisitorVO>)request.getAttribute("list");
    if (list != null) {
  %>
    <table>
      <%
        for (VisitorVO vo : list) {
      %>
        <tr>
          <td><%= vo.getName() %></td>
          <td><%= vo.getWriteDate() %></td>
          <td><%= vo.getContent() %></td>
        </tr>
      <%
        }
      %>
    </table>
  <%
    } else {
  %>
  <h2>${requestScope.msg}</h2>
  <% } %>

  <hr/>
  <a href="mvc/visitorForm.html">메인화면</a>
</body>
</html>
