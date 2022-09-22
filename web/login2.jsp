<%@ page language="java" contentType="text/html"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
	// 스크립트릿: java 코드 삽입
	String uid = request.getParameter("id");
	String res = "<h1>유저 ID: " + uid + "</h1>";
	out.println(res);
	
	Date now = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String date = df.format(now);
	
	if (date.endsWith("17")) {
		response.sendRedirect("underCheck.jsp");
		return;
	}
%>

<%@ include file="loginTime.jsp" %>
<%@ include file="getHttpHeaders.jsp" %>
