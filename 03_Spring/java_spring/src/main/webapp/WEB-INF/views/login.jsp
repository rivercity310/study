<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <meta HTTP-EQUIV="Content-Type" content="text/html; charset=UTF-8">
    <title>로그인</title>
</head>

<body>
    <div style="text-align: center;">
        <h1>로그인</h1>
        <hr/>
        <form action="login_proc.jsp" method="post">
            <table border="1" cellpadding="0" cellspacing="0">
                <tr>
                    <td bgcolor="orange">ID</td>
                    <td><input type="text" name="id"/></td>
                </tr>
                <tr>
                    <td bgcolor="orange">PW</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Submit"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>