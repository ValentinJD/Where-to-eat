<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="fragments/headTag.jsp"/>

<html>

<body class="main">
<div class="restaurant">
    <jsp:include page="fragments/bodyHeader.jsp"/>

    <section>

<%--        <h2>${param.action == 'create' ? 'Create restaurant' : 'Edit restaurant'}</h2>--%>

        <jsp:useBean id="restaurant" type="ru.whereToEat.model.Restaurant" scope="request"/>
    <h3><spring:message code="${restaurant.isNew() ? 'common.create' : 'common.update'}"/></h3>
        <form method="post" action="restaurants">
            <input type="hidden" name="restaurantId" value="${restaurant.id}">
            <dl>
                <dt><spring:message code="restaurant.title"/>:</dt>
                <dd><input type="text" value="${restaurant.name}" size=50 name="name" required></dd>
            </dl>


            <input type="hidden" value="${restaurant.vote_count}" name="count" required>

            <button type="submit"><spring:message code="common.save"/></button>
            <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
        </form>
    </section>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
