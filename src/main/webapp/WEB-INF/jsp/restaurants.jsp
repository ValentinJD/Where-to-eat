<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ page import="ru.whereToEat.web.SecurityUtil" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/headTag.jsp"/>

<html>

<body>
<script type="text/javascript" src="resources/js/vote.common.js" defer></script>
<script type="text/javascript" src="resources/js/vote.meals.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<p class="restaurant"><spring:message code="user.name"/> : <%=SecurityUtil.getUserName() %>
</p>

<div class="container">
    <div class="card">
        <div class="card-body">
            <p><spring:message code="app.filter"/></p>
            <form method="get" action="restaurants/filter">
                <input type="hidden" name="action" value="filter">
                <dl>
                    <dd><input name="nameRestaurant" value=""
                               placeholder=
                               <spring:message code="restaurant.title"/> size="80%"></dd>
                    <button class="btn btn-dark" type="submit"><spring:message code="common.filter"/></button>
                </dl>
            </form>
        </div>
    </div>
</div>

<div class="container">
    <c:forEach var="restaurant" items="${restaurants}">
        <div class="card bg-light">
            <div class="card-body">
                <div class="row">
                    <div class="col">
                        <h2 class="bg-info text-center"><spring:message code="restaurant.title"/>
                            : ${restaurant.name}
                            <table class="container table table-striped bg-light" align="center" border="1px">
                                <tr>
                                    <td><spring:message code="voter.count"/></td>
                                    <td><spring:message code="voter.for"/></td>
                                    <td><spring:message code="voter.against"/></td>
                                </tr>
                                <tr>
                                    <td>${restaurant.vote_count}</td>
                                    <td><a class="btn btn-primary"
                                           href="restaurants/vote?restaurantId=<c:out value="${restaurant.id}"/>&count=1"
                                           class="c">+</a></td>
                                    <td><a class="btn btn-primary"
                                           href="restaurants/vote?restaurantId=<c:out value="${restaurant.id}&count=-1"/>"
                                           class="c">-</a></td>
                                </tr>
                            </table>
                        </h2>
                    </div>
                </div>
                <p>
                    <a class="btn btn-primary"
                       href="restaurants/update?restaurantId=<c:out value="${restaurant.id}"/>"
                       class="c"><spring:message code="common.update"/></a>
                    <a class="btn btn-danger"
                       href="restaurants/delete?restaurantId=<c:out value="${restaurant.id}"/>"
                       class="c"><spring:message code="common.delete"/></a></p>
                <br>
                <div class="text-center p-3">
                    <p class="btn-info"><spring:message code="restaurants.menu"/></p>
                </div>
                <table class="display container table table-striped" align="center" id="${restaurant.id}">
                    <thead>
                    <tr>
                        <td><spring:message code="app.description"/></td>
                        <td><spring:message code="app.price"/></td>
                        <td><spring:message code="common.update"/></td>
                        <td><spring:message code="common.delete"/></td>
                    </tr>
                    </thead>
                    <c:forEach var="meal1" items="${restaurant.menu}">
                        <tr>
                            <jsp:useBean id="meal1" type="ru.whereToEat.model.Meal"/>
                            <td>${meal1.description}</td>
                            <td>${meal1.price}</td>
                            <td>
                                <a class="btn btn-primary"
                                   href="meals/update?mealId=<c:out value="${meal1.id}&restaurantId=${restaurant.id}"/>"
                                   class="c"><spring:message code="common.update"/></a>
                            </td>
                            <td><a onclick="deleteRowMeal(${meal1.id}, ${restaurant.id})" class="btn btn-danger"

                                   class="c"><spring:message code="common.delete"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
                <a onclick="addMeal(${restaurant.id})" class="btn btn-info" class="c">
                    <spring:message code="restaurants.additemmenu"/></a>
            </div>
        </div>
        <br>
    </c:forEach>
    <div class="card">
        <div class="card-body">
            <a href="restaurants/create" class="btn btn-info"><spring:message code="restaurants.create"/></a>
        </div>
    </div>
</div>


<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="common.create"/></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="mealId" name="mealId" value="">
                    <input type="hidden" id="restaurantId" name="restaurantId" >

   <%--                 <div class="form-group">
                        <label for="mealId" class="col-form-label"></label>
                        <input type="text" class="form-control" id="mealId" name="mealId"
                               placeholder="mealId">
                    </div>--%>

<%--                    <div class="form-group">
                        <label for="restaurantId" class="col-form-label"></label>
                        <input type="text" class="form-control" id="restaurantId" name="restaurantId"
                               placeholder="">
                    </div>--%>

                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message code="app.description"/></label>
                        <input type="text" class="form-control" id="description" name="description"
                               placeholder="<spring:message code="app.description"/>">
                    </div>

                    <div class="form-group">
                        <label for="price" class="col-form-label"><spring:message code="app.price"/></label>
                        <input type="number" class="form-control" id="price" name="price"
                               placeholder="<spring:message code="app.price"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="saveMeal()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>