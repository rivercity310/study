<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form action="/insertBoard.do" method="post">
    id: <input name="id" /> <br/>
    name: <input name="name" /> <br/>
    nation: <input name="nation" /> <br/>
    location: <input name="location" /> <br/>
    Reward: <input name="reward" /> <br/>
    Position: <input name="position" /> <br/>
    Content: <input name="content" /> <br/>
    Tech: <input name="tech" /> <br/>
    <input type="submit" value="제출" />
</form>

</body>
</html>