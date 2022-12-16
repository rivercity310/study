<%@ page import="com.springbook.biz.board.BoardVO" %>
<%@ page import="com.springbook.biz.board.impl.BoardDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // DB 연동 처리
    BoardDAO boardDAO = new BoardDAO();
    List<BoardVO> boardList = boardDAO.getBoardList();
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>글 목록</title>
</head>

<body>
<center>
    <h1>글 목록</h1>
    <h3>테스트님 환영합니다..<a href="logout_proc.jsp">Logout</a></h3>

    <!-- 검색 시작 -->
    <form action="getBoardList.jsp" method="post">
        <table border="1" cellspacing="0" cellpadding="0" width="700">
            <tr>
                <td align="right">
                    <select name="searchCondition">
                        <option value="Title">제목</option>
                        <option value="Content">내용</option>
                    </select>
                    <input name="searchKeyword" type="text"/>
                    <input type="submit" value="검색"/>
                </td>
            </tr>
        </table>
    </form>

    <table border="1" cellpadding="0" cellspacing="0" width="700">
        <tr>
            <th bgcolor="orange" width="100">번호</th>
            <th bgcolor="orange" width="200">제목</th>
            <th bgcolor="orange" width="150">작성자</th>
            <th bgcolor="orange" width="150">등록일</th>
            <th bgcolor="orange" width="100">조회수</th>
        </tr>

        <% for (BoardVO board : boardList) { %>
        <tr>
            <td><%= board.getSeq() %></td>
            <td align="left"><a href="getBoard.jsp?seq=<%= board.getSeq() %>" ><%= board.getTitle() %></a></td>
            <td><%= board.getWriter() %></td>
            <td><%= board.getRegDate() %></td>
            <td><%= board.getCnt() %></td>
        </tr>
        <% } %>
    </table>

    <br/>

    <a href="insertBoard.jsp">글쓰기</a>
</center>
</body>
</html>