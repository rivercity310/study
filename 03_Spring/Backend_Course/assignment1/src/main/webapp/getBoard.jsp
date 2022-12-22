<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>수정하기</title>
</head>

<body>
<center>
    <h1>글 수정</h1>
    <form action="updateBoard.do" method="post">
        <input type="hidden" name="seq" value="${ board.getSeq() }" />

        <table>
            <tr>
                <td>회사ID</td>
                <td align="left">${ board.getId() }</td>
            </tr>

            <tr>
                <td>회사명</td>
                <td align="left"><input value="${ board.getName() }" /></td>
            </tr>

            <tr>
                <td>국가</td>
                <td align="left"><input value="${ board.getNation() }" /></td>
            </tr>

            <tr>
                <td>위치</td>
                <td align="left"><input value="${ board.getLocation() }" /></td>
            </tr>

            <tr>
                <td>reward</td>
                <td align="left">
                    <input name="reward" value="${ board.getReward() }"/>
                </td>
            </tr>

            <tr>
                <td>position</td>
                <td><input name="position" value="${ board.getPosition() }" /></td>
            </tr>

            <tr>
                <td>content</td>
                <td align="left"><input name="content" value="${ board.getContent() }" /></td>
            </tr>

            <tr>
                <td>tech</td>
                <td align="left"><input name="tech" value="${ board.getTech() }"/></td>
            </tr>

            <tr>
                <td>
                    <input type="submit" value="수정" />
                </td>
            </tr>
        </table>
    </form>

    <hr/>

    <a href="deleteBoard.do?seq=${ board.getSeq() }">글삭제</a>&nbsp;&nbsp;&nbsp;
    <a href="getBoardList.do">글목록</a>
</center>
</body>
</html>