<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="fragments/headTag.jsp"/>

<html>

<body class="main">
<div class="restaurant">
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>

    <h2>${param.action == 'create' ? 'Create restaurant' : 'Edit restaurant'}</h2>
    <jsp:useBean id="restaurant" type="ru.whereToEat.model.Restaurant" scope="request"/>
    <form method="post" action="restaurants">
<%--        <input type="text" name="restaurantId" value="${restaurant.id}">--%>
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${restaurant.name}" size=50 name="name" required></dd>
        </dl>
<%--        <dl>
            <dt>Count:</dt>
            <dd><input type="number" value="${restaurant.vote_count}" name="count" required></dd>
        </dl>--%>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
