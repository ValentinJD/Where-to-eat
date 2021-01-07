<%--<%@ page import="ru.javawebinar.topjava.model.Meal" %>--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="messages.app"/>
<jsp:include page="fragments/headTag.jsp"/>
<html lang="ru">
<jsp:include page="fragments/bodyHeader.jsp"/>
<body class="main">
<div class="restaurant">

    <h2>${param.action == 'create' ? 'Create vote' : 'Edit vote'}</h2>

    <jsp:useBean id="vote" class="ru.whereToEat.model.Vote" scope="request"/>

    <form name="vote" action="votes?action=listVotes" method="post" accept-charset="utf-8">

        <input value="<c:out value="${vote.id}"/>" type="hidden" name="voteId">

        <dl>
            <dt><fmt:message key="user.id"/>:</dt>
            <dd><input value="<c:out value="${vote.userId}"/>" type="text" name="userId"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="voter.date"/></dt>
            <dd><input type="datetime-local" value="${vote.date_vote}" name="registered">

        </dl>
        <dl>
            <dt><fmt:message key="restaurants.id"/></dt>
            <dd><input type="number" value="<c:out value="${vote.restaurantId}"/>" name="restaurantId"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="voter.count"/></dt>
            <dd><input type="number" value="<c:out value="${vote.vote}"/>" name="vote"></dd>
        </dl>

        <input type="submit" value=<fmt:message key="common.save"/>>
    </form>
</div>
</body>