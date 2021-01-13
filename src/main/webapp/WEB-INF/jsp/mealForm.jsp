<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="fragments/headTag.jsp"/>

<html>

<body>

<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="restaurant">
    <section>
        <%--        <h2>${param.action == 'create' ? '<spring:message code="user.create"/>' : '<spring:message code="user.edit"/>'}</h2>--%>
        <h3><spring:message code="${meal.isNew() ? 'common.create' : 'common.update'}"/></h3>
        <jsp:useBean id="meal" type="ru.whereToEat.model.Meal" scope="request"/>
        <form method="post" action="meals">
            <input type="hidden" name="mealId" value="${meal.id}">
            <input type="hidden" value="${param.restaurantId}" name="restaurantId" required>

            <dl>
                <dt><spring:message code="app.description"/>:</dt>
                <dd><input type="text" value="${meal.description}" size=50 name="description" required></dd>
            </dl>
            <dl>
                <dt><spring:message code="app.price"/>:</dt>
                <dd><input type="number" value="${meal.price}" name="price" required></dd>
            </dl>


            <button type="submit"><spring:message code="common.save"/></button>
            <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
        </form>
    </section>
</div>
</body>
</html>
