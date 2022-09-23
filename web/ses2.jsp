<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-23
  Time: 오후 5:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%
    String membername = (String)session.getAttribute("member_id");
    if (membername != null) {
      session.removeAttribute("member_id");
      session.removeAttribute("member_passwd");
    %>
      <script>
        alert("성공적으로 로그아웃");
      </script>
    <%
      } else {
    %>
      <script>
        alert("로그인 상태 아님");
      </script>
    <%
      }
    %>
    <h2>로그인</h2><hr/>
    <form method="post" action="ses.jsp">
      <input placeholder="계정 입력" name="membername" required/><br/>
      <input type="password" placeholder="암호 입력" name="memberpassword" required/><br/>
      <input type="submit" value="login"/>
    </form>
</body>
</html>
