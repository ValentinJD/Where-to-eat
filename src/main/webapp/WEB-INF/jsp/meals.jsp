<%@ page import="ru.whereToEat.web.SecurityUtil" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/headTag.jsp"/>

<html>

<body class="main">
<div class="restaurant">
<jsp:include page="fragments/bodyHeader.jsp"/>
<p class="restaurant">Имя пользователя : <%=SecurityUtil.getUserName() %></p>
<h2><spring:message code="restaurants.menu"/></h2>
<table align="center" border="1">

    <tr>
        <th><spring:message code="user.id"/></th>
        <th><spring:message code="app.description"/></th>
        <th><spring:message code="app.price"/></th>
        <th><spring:message code="restaurants.id"/></th>
    </tr>

    <c:forEach var="meal" items="${meals}">
        <tr>
            <jsp:useBean id="meal" type="ru.whereToEat.model.Meal"/>

            <td>${meal.id}</td>
            <td>${meal.description}</td>
            <td>${meal.price}</td>
            <td>${meal.restaurant.id}</td>
            <td><a href="meals/update?mealId=<c:out value="${meal.id}"/>"><spring:message code="common.update"/></a></td>
            <td><a href="meals/delete?mealId=<c:out value="${meal.id}"/>"><spring:message code="common.delete"/></a></td>

        </tr>
    </c:forEach>
</table>
<a href="meals/create" class="c"><spring:message code="common.create"/></a>
<jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>