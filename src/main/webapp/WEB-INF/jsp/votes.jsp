<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/headTag.jsp"/>
<html>
<jsp:include page="fragments/bodyHeader.jsp"/>
<body class="main">
<div class="restaurant">
    <h2><spring:message code="voter.list"/></h2>
    <table align="center" border="1">

        <tr>
            <th><spring:message code="voter.id"/></th>
            <th><spring:message code="user.id"/></th>
            <th><spring:message code="voter.date"/></th>
            <th><spring:message code="restaurants.id"/></th>
            <th><spring:message code="voter.count"/></th>

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
                <td><a href="votes/edit?voteId=<c:out value="${vote.id}"/>" class="c"><spring:message
                        code="common.update"/></a></td>
                <td><a href="votes/delete?voteId=<c:out value="${vote.id}"/>" class="c"><spring:message
                        code="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="votes/create" class="c"><spring:message code="common.create"/></a>
</div>
</body>
</html>