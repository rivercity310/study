<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-24
  Time: 오후 5:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <% if (request.getMethod().equals("GET")) { %>
      <h2>요청 폼</h2>
      <hr/>
      <form method="POST" action="setProperty_tag.jsp">
        예약자명 : <input type="text" name="bookerName"/><br/>
        예약컬러 : <input type="color" name="bookerColor"/><br/>
        예약일시 : <input type="datetime-local" name="bookerDateTime"/><br/>
        <input type="submit" value="예약 완료"/>
      </form>
  <% } else {
      request.setCharacterEncoding("utf-8");
      %>
      <h2>setProperty 액션 태그 예제</h2>
      <hr/>
      <jsp:useBean id="booker" class="jspbean.BookerInfo" />

      <%-- 다음 세 줄은 <jsp:setProperty name="booker" property="*"/> 와 동일 --%>
      <jsp:setProperty name="booker" property="bookerName" value='<%= request.getParameter("bookerName")%>'/>
      <jsp:setProperty name="booker" property="bookerColor" param="bookerColor"/>
      <jsp:setProperty name="booker" property="bookerDateTime" />


      예약자명 : <jsp:getProperty name="booker" property="bookerName"/><br/>
      예약컬러 : <jsp:getProperty name="booker" property="bookerColor"/><br/>
      예약일시 : <jsp:getProperty name="booker" property="bookerDateTime"/><br/>
    <%
    }
  %>
</body>
</html>
