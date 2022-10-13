<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-24
  Time: 오후 4:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%
    String countries[] = {"korea", "english", "japan"};

    for (String str : countries) {
      out.print(str + " : ");
  %>
      <jsp:include page="greeting.jsp">
        <jsp:param name="country" value="<%=str%>" />
      </jsp:include>
  <%
      out.print("<br/>");
    }
  %>
</body>
</html>
