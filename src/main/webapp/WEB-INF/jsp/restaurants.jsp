<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ page import="ru.whereToEat.web.SecurityUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/headTag.jsp"/>

<html>

<body class="main">

<div class="op">
    <jsp:include page="fragments/bodyHeader.jsp"/>
    <p class="restaurant">Имя пользователя : <%=SecurityUtil.getUserName() %></p>
    <div>
        <p>Фильтровать по имени ресторана</p>
        <form method="get" action="restaurants">
            <input type="hidden" name="action" value="filter">
            <dl>
                <dd><input name="nameRestaurant" value="" placeholder="Введите наименование ресторана" size="80%"></dd>
                <button type="submit">Filter</button>
            </dl>
        </form>
    </div>

    <c:forEach var="restaurant" items="${restaurants}">

        <div class="restaurant">
            <div>
                <p>Ресторан ${restaurant.name}</p>
                <table align="center" border="1px">
                    <tr>

                        <td>Голоса</td>
                        <td>За</td>
                        <td>Против</td>
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


                <a href="restaurants?action=update&restaurantId=<c:out value="${restaurant.id}"/>" class="c">Update</a>
                <a href="restaurants?action=delete&restaurantId=<c:out value="${restaurant.id}"/>" class="c">Delete</a>


                <p>Меню</p>
                <table align="center" border="1px">
                    <tr>
                        <td>Наименование</td>
                        <td>Цена, руб</td>
                        <td>Обновить</td>
                        <td>Удалить</td>
                    </tr>
                    <c:forEach var="meal1" items="${restaurant.menu}">

                        <tr>
                            <jsp:useBean id="meal1" type="ru.whereToEat.model.Meal"/>
                                <%--                        <td>${meal1.id}</td>--%>
                            <td>${meal1.description}</td>
                            <td>${meal1.price}</td>
                            <td><a href="meals?action=update&mealId=<c:out value="${meal1.id}
                        &restaurantId=${restaurant.id}"/>" class="c">Update</a></td>
                            <td><a href="meals?action=delete&mealId=<c:out value="${meal1.id}
                        &restaurantId=${restaurant.id}"/>" class="c">Delete</a></td>
                        </tr>

                    </c:forEach>
                </table>
                <br>
                <a href="meals?action=create&restaurantId=${restaurant.id}" class="c">Добавить меню</a>
            </div>
        </div>
        <br>
    </c:forEach>
    <div>
        <a href="restaurants?action=create" class="c">Создать новый ресторан</a>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>