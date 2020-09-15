<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Votes</title>
</head>
<body>
<hr>
<h3><a href="index.html">На главную</a></h3>
<hr>
<h2>Votes List</h2>
<table align="center" border="1">

    <tr>
        <th>voteId</th>
        <th>userId</th>
        <th>date_vote</th>
        <th>restaurantId</th>
        <th>vote</th>

    </tr>

    <c:forEach var="vote" items="${votes}">
        <tr>
            <jsp:useBean id="vote" type="ru.whereToEat.model.Vote"/>

            <td>${vote.id}</td>
            <td>${vote.userId}</td>
            <td><%=vote.getDate_vote().format(TimeUtil.format)%>
            <td>${vote.date_vote}</td>
            <td>${vote.vote}</td>

        </tr>
    </c:forEach>
</table>
</body>
</html>