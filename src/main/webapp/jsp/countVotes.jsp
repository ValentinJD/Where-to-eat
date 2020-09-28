<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CountVotes</title>
</head>
<body>
<hr>
<h3><a href="index.html">На главную</a></h3>
<hr>
<h2>CountVotes List</h2>
<table align="center" border="1">

    <tr>
        <th>countVoteId</th>
        <th>Date</th>
        <th>restaurantId</th>
        <th>count</th>
    </tr>

    <c:forEach var="countVote" items="${countVotes}">
        <tr>
            <jsp:useBean id="countVote" type="ru.whereToEat.model.CountVote"/>

            <td>${countVote.id}</td>
            <td>${countVote.date}</td>
            <td>${countVote.restaurantId}</td>
            <td>${countVote.count}</td>
            <td><a href="countVotes?action=edit&voteId=<c:out value="${countVote.id}"/>">Update</a></td>
            <td><a href="countVotes?action=delete&voteId=<c:out value="${countVote.id}"/>">Delete</a></td>

        </tr>

    </c:forEach>
</table>
<a href="countVotes?action=create" >Создать</a>
</body>
</html>