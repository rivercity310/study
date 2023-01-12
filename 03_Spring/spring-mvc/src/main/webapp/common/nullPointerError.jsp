<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>

<html>
<head><title>NPE 에러화면</title></head>
<body bgcolor="#ffffff" text="#000000">
<table width="100%" border="1" cellspacing="0" cellpadding="0">
    <tr><td align="center" bgcolor="orange"><b>NPE 에러화면</b></td> </tr>
</table>

<br/>

<table width="100%" border="1" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <td align="center">
            <br/><br/><br/><br/><br/>
            Message: ${exception.message}
            <br/><br/><br/><br/><br/>
        </td>
    </tr>
</table>
</body>
</html>