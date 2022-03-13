<%--
  Created by IntelliJ IDEA.
  User: kukjunlee
  Date: 2022/02/24
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RegisterForm</title>
</head>
<body>
    <h1>
        회원가입 FORM
    </h1>
    <form action="/register/register.do" method="post">
        id: <input type="text" name="id"><br>
        password: <input type="password" name="password"><br>
        email: <input type="text" name="email"><br>
        name: <input type="text" name="name"><br>
        <input type="submit" value="확인">
        <input type="reset" value="취소">
    </form>
</body>
</html>
