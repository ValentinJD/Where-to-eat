<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restaurants</title>
</head>
<body>
<hr>
<h3><a href="index.html">На главную</a></h3>
<hr>
<h2>Restaurants List</h2>
<table align="center" border="1">

    <tr>
        <th>restaraunt_Id</th>
        <th>name_restaraunt</th>
        <th>vote_count</th>
        <th></th>
        <th></th>
        <th>Голос за</th>
        <th>Голос против</th>
    </tr>

    <c:forEach var="restaurant" items="${restaurants}">
        <tr>
            <jsp:useBean id="restaurant" type="ru.whereToEat.model.Restaurant"/>

            <td>${restaurant.id}</td>
            <td>${restaurant.name}</td>
            <td>${restaurant.vote_count}</td>
            <td><a href="restaurants?action=update&restaurantId=<c:out value="${restaurant.id}"/>">Update</a>
            </td>
            <td><a href="restaurants?action=delete&restaurantId=<c:out value="${restaurant.id}"/>">Delete</a>
            </td>
            <td><a href="restaurants?action=vote&restaurantId=<c:out value="${restaurant.id}"/>
                         &count=1">За</a>
            </td>
            <td><a href="restaurants?action=vote&restaurantId=<c:out value="${restaurant.id}
                         &count=-1"/>">Против</a>
            </td>
        </tr>

        <c:forEach var="meal" items="${restaurant.menu}">
            <tr>
                <jsp:useBean id="meal" type="ru.whereToEat.model.Meal"/>
                <td>${meal.id}</td>
                <td></td>
                <td>${meal.description}</td>
                <td>${meal.price}</td>
                <td></td>
            </tr>

        </c:forEach>
    </c:forEach>
</table>
<a href="restaurants?action=create">Создать</a>
</body>
</html>