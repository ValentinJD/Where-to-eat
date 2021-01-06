<%@ page import="ru.whereToEat.web.SecurityUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="fragments/headTag.jsp"/>

<html>

<body class="main">

<jsp:include page="fragments/bodyHeader.jsp"/>
<p class="restaurant">Имя пользователя : <%=SecurityUtil.getUserName() %></p>
<h2>Meals List</h2>
<table align="center" border="1">

    <tr>
        <th>mealId</th>
        <th>description</th>
        <th>price</th>
        <th>restaurantId</th>
    </tr>

    <c:forEach var="meal" items="${meals}">
        <tr>
            <jsp:useBean id="meal" type="ru.whereToEat.model.Meal"/>

            <td>${meal.id}</td>
            <td>${meal.description}</td>
            <td>${meal.price}</td>
            <td>${meal.restaurant.id}</td>
            <td><a href="meals?action=update&mealId=<c:out value="${meal.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>

        </tr>
    </c:forEach>
</table>
<a href="meals?action=create" class="c">Создать</a>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>