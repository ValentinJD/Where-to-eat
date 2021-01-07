<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages.app"/>
<jsp:include page="fragments/headTag.jsp"/>
<html>
<jsp:include page="fragments/bodyHeader.jsp"/>
<body class="main">
<div class="restaurant">
    <h2><fmt:message key="voter.list"/></h2>
    <table align="center" border="1">

        <tr>
            <th><fmt:message key="voter.id"/></th>
            <th><fmt:message key="user.id"/></th>
            <th><fmt:message key="voter.date"/></th>
            <th><fmt:message key="restaurants.id"/></th>
            <th><fmt:message key="voter.count"/></th>

        </tr>

        <jsp:useBean id="votes" scope="request" type="java.util.List"/>

        <c:forEach var="vote" items="${votes}">
            <tr>
                <jsp:useBean id="vote" type="ru.whereToEat.model.Vote"/>

                <td>${vote.id}</td>
                <td>${vote.userId}</td>
                <td><%=vote.getDate_vote().format(TimeUtil.format)%>
                <td>${vote.restaurantId}</td>
                <td>${vote.vote}</td>
                <td><a href="votes?action=edit&voteId=<c:out value="${vote.id}"/>" class="c"><fmt:message key="common.update"/></a></td>
                <td><a href="votes?action=delete&voteId=<c:out value="${vote.id}"/>" class="c"><fmt:message key="common.delete"/></a></td>

            </tr>

        </c:forEach>
    </table>
    <a href="votes?action=create" class="c"><fmt:message key="common.create"/></a>
</div>
</body>
</html>