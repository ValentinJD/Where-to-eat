<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ page import="ru.whereToEat.web.SecurityUtil" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/headTag.jsp"/>

<html>

<body class="main">

<div class="op">
    <jsp:include page="fragments/bodyHeader.jsp"/>
    <p class="restaurant"><spring:message code="user.name"/> : <%=SecurityUtil.getUserName() %>
    </p>
    <div>
        <p><spring:message code="app.filter"/></p>
        <form method="get" action="restaurants/filter">
            <input type="hidden" name="action" value="filter">
            <dl>
                <dd><input name="nameRestaurant" value=""
                           placeholder=<spring:message code="restaurant.title"/> size="80%"></dd>
                <button type="submit"><spring:message code="common.filter"/></button>
            </dl>
        </form>
    </div>

    <c:forEach var="restaurant" items="${restaurants}">

        <div class="restaurant">
            <div>
                <p><spring:message code="restaurant.title"/> : ${restaurant.name}</p>
                <table align="center" border="1px">
                    <tr>
                        <td><spring:message code="voter.count"/></td>
                        <td><spring:message code="voter.for"/></td>
                        <td><spring:message code="voter.against"/></td>
                    </tr>
                    <tr>

                        <td>${restaurant.vote_count}</td>
                        <td><a href="restaurants/vote?restaurantId=<c:out value="${restaurant.id}"/>&count=1"
                               class="c">+</a></td>
                        <td><a href="restaurants/vote?restaurantId=<c:out value="${restaurant.id}&count=-1"/>"
                               class="c">-</a></td>
                    </tr>
                </table>
                <br>


                <a href="restaurants/update?restaurantId=<c:out value="${restaurant.id}"/>"
                   class="c"><spring:message code="common.update"/></a>
                <a href="restaurants/delete?restaurantId=<c:out value="${restaurant.id}"/>"
                   class="c"><spring:message code="common.delete"/></a>


                <p><spring:message code="restaurants.menu"/></p>
                <table align="center" border="1px">
                    <tr>
                        <td><spring:message code="app.description"/></td>
                        <td><spring:message code="app.price"/></td>
                        <td><spring:message code="common.update"/></td>
                        <td><spring:message code="common.delete"/></td>
                    </tr>
                    <c:forEach var="meal1" items="${restaurant.menu}">

                        <tr>
                            <jsp:useBean id="meal1" type="ru.whereToEat.model.Meal"/>
                            <td>${meal1.description}</td>
                            <td>${meal1.price}</td>
                            <td>
                                <a href="meals/update?mealId=<c:out value="${meal1.id}&restaurantId=${restaurant.id}"/>"
                                   class="c"><spring:message code="common.update"/></a>
                            </td>
                            <td><a href="meals/delete?mealId=<c:out value="${meal1.id}&restaurantId=${restaurant.id}"/>"
                                   class="c"><spring:message code="common.delete"/></a>
                            </td>
                        </tr>

                    </c:forEach>
                </table>
                <br>
                <a href="meals/create?restaurantId=${restaurant.id}" class="c">
                    <spring:message code="restaurants.additemmenu"/></a>
            </div>
        </div>
        <br>
    </c:forEach>
    <div>
        <a href="restaurants/create" class="c"><spring:message code="restaurants.create"/></a>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>