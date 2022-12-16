<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <meta HTTP-EQUIV="Content-Type" content="text/html; charset=UTF-8">
    <title>회원가입</title>
</head>

<body>
<center>
    <h1>회원가입</h1>
    <hr/>
    <form action="signIn_proc.jsp" method="post">
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
                <td bgcolor="orange">Name</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Submit"/>
                </td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>