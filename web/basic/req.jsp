<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-23
  Time: 오후 5:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <% if (request.getMethod().equals("GET")) { %>
    <h2>원하는 컬러와 날짜 선택</h2>
    <form method="post" action="req.jsp">
      컬러 : <input type="color" name="color"/><br/>
      날짜 : <input type="date" name="date"/><br/>
      <input type="submit" value="전송"/>
    </form>
  <% } else { %>
    <script>
      document.body.style.backgroundColor = '<%= request.getParameter("color") %>'
    </script>
    <h2>선택 날짜는 <%= request.getParameter("date") %> 이네요...</h2>
  <% } %>
</body>
</html>
