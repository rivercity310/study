<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-30
  Time: 오후 3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jspbean.EncDecService, jspbean.MemberVO" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h2>회원 정보</h2>
  <hr/>

  <ul>
    <li>이름: ${membervo.name}</li>
    <li>계정: ${requestScope.membervo.id}</li>
    <li>암호: ${requestScope.membervo.passwd}(${EncDecService.decrypt(requestScope.membervo.passwd)})</li>
    <li>전화번호: ${requestScope.membervo.phoneNum}</li>
  </ul>
</body>
</html>
