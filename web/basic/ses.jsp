<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-23
  Time: 오후 5:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%
    if (request.getMethod().equals("POST")) {
      if (session.isNew() || session.getAttribute("member_id") == null) {
        String name = request.getParameter("membername");
        String passwd = request.getParameter("memberpassword");

        if (name.equals("duke") && passwd.equals("java")) {
          session.setAttribute("member_id", name);
          session.setAttribute("member_passwd", passwd);
          session.setMaxInactiveInterval(60);
  %>
          <script>
            alert("로그인 성공");
          </script>
          <h3><%= name %> 회원님! 좋은 하루 되세요...</h3>
          <a href="ses2.jsp">로그아웃</a>
    <% }
        else {
    %>
            <script>
              alert("로그인 실패");
            </script>
            <h2>로그인</h2><hr/>
            <form method="post">
              <input placeholder="계정 입력" name="membername" required/><br/>
              <input type="password" placeholder="암호 입력" name="memberpassword" required/><br/>
              <input type="submit" value="login"/>
            </form>
        <%
        }
      }
    } else if (request.getMethod().equals("GET")) {
      String name = (String)session.getAttribute("member_id");
      if (name != null) {
      %>
        <h3><%= name %> 회원님! 좋은 하루~</h3>
        <a href="ses2.jsp">로그아웃</a>
      <%
      } else {
      %>
        <script>
          alert("로그인 해주세요");
        </script>
        <h2>로그인</h2><hr/>
        <form method="post">
          <input placeholder="계정 입력" name="membername" required/><br/>
          <input type="password" placeholder="암호 입력" name="memberpassword" required/><br/>
          <input type="submit" value="login"/>
        </form>
     <%
      }
    }
    %>

</body>
</html>
