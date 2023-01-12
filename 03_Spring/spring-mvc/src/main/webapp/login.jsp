<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <meta HTTP-EQUIV="Content-Type" content="text/html; charset=UTF-8">
    <title>로그인</title>
</head>

<body>
    <center>
        <h1>Login</h1>
        <hr/>
        <form action="login.do" method="post">
            <table border="1" cellpadding="0" cellspacing="0">
                <tr>
                    <td bgcolor="orange">ID</td>
                    <td><input type="text" name="id" value="${ userVO.getId() }"/></td>
                </tr>
                <tr>
                    <td bgcolor="orange">PW</td>
                    <td><input type="password" name="password" value="${ userVO.getPassword() }"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="제출"/>
                    </td>
                </tr>
            </table>
        </form>
    </center>

    <center>
        <h3><a href="signIn.jsp">회원가입</a></h3>
    </center>
</body>
</html>