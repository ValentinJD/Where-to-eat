<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<hr>
<h3><a href="index.html">На главную</a></h3>
<hr>
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
            <td>${meal.restaurant.restaraunt_Id}</td>
            <td><a href="meals?action=update&mealId=<c:out value="${meal.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>

        </tr>
    </c:forEach>
</table>
<a href="meals?action=create" >Создать</a>
</body>
</html>