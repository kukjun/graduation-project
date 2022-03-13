<%--
  Created by IntelliJ IDEA.
  User: kukjunlee
  Date: 2022/02/24
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WeatherInputLocalInfo</title>
</head>
<body>
    <p>
        지역정보를 입력하세요.<br>
        시, 구, 동 순서대로 입력해주세요
    </p>

    <form action="/api/weather/inputLocalInfo.do" method="post">
        시: <input type="text" name="localLevel1"> <br>
        구: <input type="text" name="localLevel2"> <br>
        동: <input type="text" name="localLevel3"> <br>
        <input type="submit" value="추가">
        <input type="reset" value="취소">
    </form>

</body>
</html>
