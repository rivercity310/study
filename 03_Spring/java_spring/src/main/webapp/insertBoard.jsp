<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>새 글 동록</title>
</head>

<body>
<center>
    <h1>새 글 등록</h1>
    <a href="logout.do">Logout</a>

    <form action="insertBoard.do" method="post" enctype="multipart/form-data">
        <table border="1" cellspacing="0" cellpadding="0">
            <tr>
                <td bgcolor="orange" width="70">제목</td>
                <td align="left">
                    <input name="title" type="text"/>
                </td>
            </tr>

            <tr>
                <td bgcolor="orange">작성자</td>
                <td align="left">
                    <input type="text" name="writer" size="10"/>
                </td>
            </tr>

            <tr>
                <td bgcolor="orange" width="70">내용</td>
                <td align="left">
                    <textarea name="content" cols="40" rows="10"></textarea>
                </td>
            </tr>

            <tr>
                <td bgcolor="orange" width="70">업로드</td>
                <td align="left">
                    <input type="file" name="uploadFile"/>
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="등록" />
                </td>
            </tr>
        </table>
    </form>

    <hr/>

    <a href="getBoardList.jsp">글목록</a>
</center>
</body>
</html>