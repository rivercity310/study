<%@ page language="java" contentType="text/html"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Date" %>

<%!
	String getDate() {
		return new Date().toString();
	}
%>

<%
	String res1 = "로그인 시간: ";
	res1 += getDate();
		
	out.print(res1);
%>
