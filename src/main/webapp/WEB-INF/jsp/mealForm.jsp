<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="messages.app"/>
<jsp:include page="fragments/headTag.jsp"/>

<html>

<body>

<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="restaurant">
    <section>
        <h2>${param.action == 'create' ? '<fmt:message key="user.create"/>' : '<fmt:message key="user.edit"/>'}</h2>
        <jsp:useBean id="meal" type="ru.whereToEat.model.Meal" scope="request"/>
        <form method="post" action="meals">
            <input type="hidden" name="mealId" value="${meal.id}">
            <input type="hidden" value="${param.restaurantId}" name="restaurantId" required>

            <dl>
                <dt><fmt:message key="app.description"/>:</dt>
                <dd><input type="text" value="${meal.description}" size=50 name="description" required></dd>
            </dl>
            <dl>
                <dt><fmt:message key="app.price"/>:</dt>
                <dd><input type="number" value="${meal.price}" name="price" required></dd>
            </dl>


            <button type="submit"><fmt:message key="common.save"/></button>
            <button onclick="window.history.back()" type="button"><fmt:message key="common.cancel"/></button>
        </form>
    </section>
</div>
</body>
</html>
