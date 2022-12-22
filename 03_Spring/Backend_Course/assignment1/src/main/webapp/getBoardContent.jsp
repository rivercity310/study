<%@ page import="com.example.assignment1.board.BoardVO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>글 상세</title>
</head>

<body>
<center>
    <h1>상세공고</h1>
    <a href="getBoardList.jsp">목록 가기</a>
    <table>
        <tr>
            <td>회사ID</td>
            <td align="left">${ boardContent.getId() }</td>
        </tr>

        <tr>
            <td>회사명</td>
            <td align="left">${ boardContent.getName() }</td>
        </tr>

        <tr>
            <td>국가</td>
            <td align="left">${ boardContent.getNation() }</td>
        </tr>

        <tr>
            <td>위치</td>
            <td align="left">${ boardContent.getLocation() }</td>
        </tr>

        <tr>
            <td>reward</td>
            <td align="left">${ boardContent.getReward() }</td>
        </tr>

        <tr>
            <td>position</td>
            <td>${ boardContent.getPosition() }</td>
        </tr>

        <tr>
            <td>tech</td>
            <td align="left">${ boardContent.getTech() }</td>
        </tr>

        <tr>
            <td>채용내용</td>
            <td align="left">${ boardContent.getContent() }</td>
        </tr>

        <tr>
            <td>이 회사가 올린 다른 공고</td>
            <c:forEach items="${boardContentList}" var="board">
                <td><a href="getBoardContent.do?seq=${board.getSeq()}&name=${board.getName()}">${ board.getContent() }</a></td>
            </c:forEach>
        </tr>

        <tr>
            <input id="btn" type="submit" value="지원" onclick="fn()" />
        </tr>
    </table>

    <hr/>

</center>
</body>
</html>

<script>
    function fn() {
        alert("지원완료")
        document.querySelector("#btn").disabled = true
    }
</script>