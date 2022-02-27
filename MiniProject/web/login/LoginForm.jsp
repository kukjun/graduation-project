<%--
  Created by IntelliJ IDEA.
  User: kukjunlee
  Date: 2022/02/24
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Form</title>
</head>
<body>
    <h1>로그인</h1>
    <form action="/auth/login.do" method="post">
        id: <input type="text" name="id"><br>
        password: <input type="password" name="password"><br>
        <input type="submit" value="확인">
        <input type="reset" value="취소">
    </form>
</body>
</html>
