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
    <h1>지역 정보를 입력해주세요</h1>
    <h2>현재 저장된 지역 정보들</h2>
    <p>대전광역시 유성구 - x:67, y=101</p>
    <p>대전광역시 유성구 온천1동 - x:66, y:100</p>
    <p>대전광역시 동구 판암동 - x:69, y:100</p>

    <form action="/api/weather/inputLocalInfo.do" method="post">
        x 좌표: <input type="text" name="x"> <br>
        y 좌표: <input type="text" name="y"> <br>
        <input type="submit" value="추가">
        <input type="reset" value="취소">
    </form>

</body>
</html>
