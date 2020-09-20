<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
    <jsp:useBean id="meal" type="ru.whereToEat.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <input type="text" name="mealId" value="${meal.id}">
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${meal.description}" size=50 name="description" required></dd>
        </dl>
        <dl>
            <dt>Price:</dt>
            <dd><input type="number" value="${meal.price}" name="price" required></dd>
        </dl>
        <dl>
            <dt>restaurantId:</dt>
            <dd><input type="number" value="${meal.restaurant.restaraunt_Id}" name="restaurantId" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
