<%@ page import="ru.whereToEat.web.SecurityUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages.app"/>
<jsp:include page="fragments/headTag.jsp"/>

<html>

<body class="main">
<div class="restaurant">
<jsp:include page="fragments/bodyHeader.jsp"/>
<p class="restaurant">Имя пользователя : <%=SecurityUtil.getUserName() %></p>
<h2><fmt:message key="restaurants.menu"/></h2>
<table align="center" border="1">

    <tr>
        <th><fmt:message key="user.id"/></th>
        <th><fmt:message key="app.description"/></th>
        <th><fmt:message key="app.price"/></th>
        <th><fmt:message key="restaurants.id"/></th>
    </tr>

    <c:forEach var="meal" items="${meals}">
        <tr>
            <jsp:useBean id="meal" type="ru.whereToEat.model.Meal"/>

            <td>${meal.id}</td>
            <td>${meal.description}</td>
            <td>${meal.price}</td>
            <td>${meal.restaurant.id}</td>
            <td><a href="meals?action=update&mealId=<c:out value="${meal.id}"/>"><fmt:message key="common.update"/></a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>"><fmt:message key="common.delete"/></a></td>

        </tr>
    </c:forEach>
</table>
<a href="meals?action=create" class="c"><fmt:message key="common.create"/></a>
<jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>