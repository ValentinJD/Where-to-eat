<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ page import="ru.whereToEat.web.SecurityUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages.app"/>
<jsp:include page="fragments/headTag.jsp"/>

<html>

<body class="main">

<div class="op">
    <jsp:include page="fragments/bodyHeader.jsp"/>
    <p class="restaurant"><fmt:message key="user.name"/> : <%=SecurityUtil.getUserName() %>
    </p>
    <div>
        <p><fmt:message key="app.filter"/></p>
        <form method="get" action="restaurants">
            <input type="hidden" name="action" value="filter">
            <dl>
                <dd><input name="nameRestaurant" value="" placeholder=
                <fmt:message key="restaurant.title"/> size="80%"></dd>
                <button type="submit"><fmt:message key="common.filter"/></button>
            </dl>
        </form>
    </div>

    <c:forEach var="restaurant" items="${restaurants}">

        <div class="restaurant">
            <div>
                <p><fmt:message key="restaurant.title"/> : ${restaurant.name}</p>
                <table align="center" border="1px">
                    <tr>
                        <td><fmt:message key="voter.count"/></td>
                        <td><fmt:message key="voter.for"/></td>
                        <td><fmt:message key="voter.against"/></td>
                    </tr>
                    <tr>

                        <td>${restaurant.vote_count}</td>
                        <td><a href="restaurants?action=vote&restaurantId=<c:out value="${restaurant.id}"/>&count=1"
                               class="c">+</a></td>
                        <td><a href="restaurants?action=vote&restaurantId=<c:out value="${restaurant.id}&count=-1"/>"
                               class="c">-</a></td>
                    </tr>
                </table>
                <br>


                <a href="restaurants?action=update&restaurantId=<c:out value="${restaurant.id}"/>"
                   class="c"><fmt:message key="common.update"/></a>
                <a href="restaurants?action=delete&restaurantId=<c:out value="${restaurant.id}"/>"
                   class="c"><fmt:message key="common.delete"/></a>


                <p><fmt:message key="restaurants.menu"/></p>
                <table align="center" border="1px">
                    <tr>
                        <td><fmt:message key="app.description"/></td>
                        <td><fmt:message key="app.price"/></td>
                        <td><fmt:message key="common.update"/></td>
                        <td><fmt:message key="common.delete"/></td>
                    </tr>
                    <c:forEach var="meal1" items="${restaurant.menu}">

                        <tr>
                            <jsp:useBean id="meal1" type="ru.whereToEat.model.Meal"/>
                            <td>${meal1.description}</td>
                            <td>${meal1.price}</td>
                            <td><a href="meals?action=update&mealId=<c:out value="${meal1.id}
                        &restaurantId=${restaurant.id}"/>" class="c"><fmt:message key="common.update"/></a></td>
                            <td><a href="meals?action=delete&mealId=<c:out value="${meal1.id}
                        &restaurantId=${restaurant.id}"/>" class="c"><fmt:message key="common.delete"/></a></td>
                        </tr>

                    </c:forEach>
                </table>
                <br>
                <a href="meals?action=create&restaurantId=${restaurant.id}" class="c"><fmt:message
                        key="restaurants.additemmenu"/></a>
            </div>
        </div>
        <br>
    </c:forEach>
    <div>
        <a href="restaurants?action=create" class="c"><fmt:message key="restaurants.create"/></a>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>