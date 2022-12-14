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
    <h1>글 상세</h1>
    <a href="logout.do">Logout</a>

    <form action="updateBoard.do" method="post">
        <input type="hidden" name="seq" value="${ board.getSeq() }" />

        <table border="1" cellspacing="0" cellpadding="0">
            <tr>
                <td bgcolor="orange" width="70">제목</td>
                <td align="left">
                    <input name="title" type="text" value="${ board.getTitle() }"/>
                </td>
            </tr>

            <tr>
                <td bgcolor="orange">작성자</td>
                <td align="left">${ board.getWriter() }</td>
            </tr>

            <tr>
                <td bgcolor="orange" width="70">내용</td>
                <td align="left">
                    <textarea name="content" cols="40" rows="10">${ board.getContent() }</textarea>
                </td>
            </tr>

            <tr>
                <td bgcolor="orange">등록일</td>
                <td align="left">${ board.getRegDate() }</td>
            </tr>

            <tr>
                <td bgcolor="orange">조회수</td>
                <td align="left">${ board.getCnt() }</td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="수정" />
                </td>
            </tr>
        </table>
    </form>

    <hr/>

    <a href="insertBoard.jsp">글등록</a>&nbsp;&nbsp;&nbsp;
    <a href="deleteBoard.do?seq=${ board.getSeq() }">글삭제</a>&nbsp;&nbsp;&nbsp;
    <a href="getBoardList.do">글목록</a>
</center>
</body>
</html>
