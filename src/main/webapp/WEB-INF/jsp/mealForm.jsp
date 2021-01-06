<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

            Description:
            <input type="text" value="${meal.description}" size=50 name="description" required>


            Price:
            <input type="number" value="${meal.price}" name="price" required>


            <input type="hidden" value="${param.restaurantId}" name="restaurantId" required>

            <button type="submit">Save</button>
            <button onclick="window.history.back()" type="button">Cancel</button>
        </form>
    </section>
</div>
</body>
</html>
