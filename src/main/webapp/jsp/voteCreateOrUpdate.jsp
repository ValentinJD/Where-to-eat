<%--<%@ page import="ru.javawebinar.topjava.model.Meal" %>--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="ru">
<head>
    <title>Обновление(Редактирование) Vote</title>
    <meta charset="utf-8">
</head>
<body style="background: burlywood  ">
<h2>${param.action == 'create' ? 'Create vote' : 'Edit vote'}</h2>

<jsp:useBean id="vote" type="ru.whereToEat.model.Vote" scope="request"/>

<form name="vote" action="votes?action=listVotes&voteId=${vote.id}
&restaurantId=${vote.restaurantId}
&dateVote=${vote.date_vote}" method="post" accept-charset="utf-8">
    <p>voteId</p>
    <input value="<c:out value="${vote.id}"/>" type="text" name="voteId" disabled>
    <p>userId</p>
    <input value="<c:out value="${vote.userId}"/>" type="text" name="userId">
    <p>dateVote</p>
    <p><c:out value="${vote.date_vote}"/></p>
    <p>restaurantId</p>
    <p><c:out value="${vote.restaurantId}"/></p>
    <p>vote</p>
    <input type="number" value="<c:out value="${vote.vote}"/>" type="text" name="vote">
    <input type="submit" value="OK">
</form>