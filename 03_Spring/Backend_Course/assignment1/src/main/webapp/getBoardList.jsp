<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>채용목록</title>
</head>

<body>
<center>
    <h1>채용목록</h1>
    <form action="getBoardList.do">
        <input name="searchKeyword"/>
        <input type="submit" value="검색"/>
    </form>
    <table>
        <tr>
            <th>회사ID</th>
            <th>회사명</th>
            <th>보상금</th>
            <th>포지션</th>
            <th>기술</th>
        </tr>

        <c:forEach items="${boardList}" var="board">
            <tr>
                <td>${board.getId()}</td>
                <td>${board.getName()}</td>
                <td>${board.getReward()}</td>
                <td>${board.getPosition()}</td>
                <td>${board.getTech()}</td>
                <td><a href="getBoard.do?seq=${board.getSeq()}">수정</a></td>
                <td><a href="getBoardContent.do?seq=${board.getSeq()}&name=${board.getName()}">상세보기</a></td>
            </tr>
        </c:forEach>
    </table>

    <br/>

    <a href="insertBoard.jsp">공고등록</a>
</center>
</body>
</html>