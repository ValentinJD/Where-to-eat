<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<hr>
<h3><a href="index.html">На главную</a></h3>
<hr>
<h2>Users List</h2>
<table align="center" border="1">

    <tr>
        <th>userId</th>
        <th>name</th>
        <th>password</th>
        <th>enabled</th>
        <th>registered</th>
        <th>role</th>
    </tr>

    <c:forEach var="user" items="${users}">
        <tr>
            <jsp:useBean id="user" type="ru.whereToEat.model.User"/>

            <td>${user.userId}</td>
            <td>${user.name}</td>
            <td>${user.password}</td>
            <td>${user.enabled}</td>
            <td><%=user.getRegistered().format(TimeUtil.format)%>
            </td>
            <td>${user.role}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>