<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>ShowWeatherInfo</title>
</head>
<body>
    <h1>날씨 정보</h1>
    <p>baseDate: ${baseDate}</p>
    <p>baseTime: ${baseTime}</p>
    <p>PTY: ${weather.PTY}</p>
    <p>REH: ${weather.REH}</p>
    <p>RN1: ${weather.RN1}</p>
    <p>T1H: ${weather.t1H}</p>
    <p>UUU: ${weather.UUU}</p>
    <p>VVV: ${weather.VVV}</p>
    <p>VEC: ${weather.VEC}</p>
    <p>WSD: ${weather.WSD}</p>

</body>
</html>
