<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-30
  Time: 오후 5:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h2>선택한 상품 정보</h2>
  <hr/>
  <%
    if (session.getAttribute("countnum") != null) { %>
    사과 : ${sessionScope.countnum.apple}<br/>
    바나나 : ${sessionScope.countnum.banana}<br/>
    한라봉 : ${sessionScope.countnum.hallabong}<br/>
  <%
  } else {
  %>
    <h2>장바구니가 비었습니다</h2>
  <% } %>

  <a href="mvc/productForm.html">돌아가기</a>
</body>
</html>
