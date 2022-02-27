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
    <p>지역 x: ${location.x}, y: ${location.y}</p>
    <p>PTY: ${location.PTY}</p>
    <p>REH: ${location.REH}</p>
    <p>RN1: ${location.RN1}</p>
    <p>T1H: ${location.t1H}</p>
    <p>UUU: ${location.UUU}</p>
    <p>VVV: ${location.VVV}</p>
    <p>VEC: ${location.VEC}</p>
    <p>WSD: ${location.WSD}</p>

</body>
</html>
