<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>User ID: <%= request.getParameter("id") %></h1>
	<h1>User PW: <%= request.getParameter("pw") %></h1>
</body>
</html>