<%--
  Created by IntelliJ IDEA.
  User: Seungsu
  Date: 2022-09-26
  Time: 오후 8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/plain; charset=UTF-8" language="java" %>
<%
  String news[] = {
          "SW 융합 클러스터, 지방 SW 교육 지원",
          "SW 교육격차 해소 방법",
          "메모리에 강한 한국, SW 정의 구현 최적지",
          "페이스북, 2019년부터 우리 정부에 세금낸다..."
  };
%>

<%-- 응답한 내용을 캐시하지 않게하여 2초마다 새로운 컨텐츠를 가져가도록 설정 --%>
<% response.setHeader("Cache-Control", "no-cache"); %>
<% int num = (int)(Math.random() * news.length); %>

{
  "news" : "<%= news[num]%>"
}