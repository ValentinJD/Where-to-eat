<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ page import="ru.whereToEat.web.SecurityUtil" %><%--<%@ page import="ru.whereToEat.util.TimeUtil" %>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/headTag.jsp"/>
<html>

<body>
<div>
    <jsp:include page="fragments/bodyHeader.jsp"/>
    <p><spring:message code="user.name"/> : <%=SecurityUtil.getUserName() %>
    </p>
    <div class="container bg-light">
        <h3><spring:message code="user.title"/></h3>

        <section>
            <button class="btn btn-primary">
                <span class="fa fa-plus"></span>
                <spring:message code="user.create"/>
            </button>
            <table class="table table-striped mt-3">
                <thead>
                <tr>
                    <th><spring:message code="user.id"/></th>
                    <th><spring:message code="user.name"/></th>
                    <th><spring:message code="user.email"/></th>
                    <th><spring:message code="user.roles"/></th>
                    <th><spring:message code="user.active"/></th>
                    <th><spring:message code="user.registered"/></th>
                    <th colspan="2"><spring:message code="user.action"/></th>
                </tr>
                </thead>

                <c:forEach items="${users}" var="user">

                    <jsp:useBean id="user" scope="page" type="ru.whereToEat.model.User"/>

                    <tr>
                        <td><c:out value="${user.id}"/></td>
                        <td><c:out value="${user.name}"/></td>
                        <td><a href="mailto:${user.email}">${user.email}</a></td>
                        <td>${user.role}</td>
                        <td><input type="checkbox" <c:if test="${user.enabled}">checked</c:if>/></td>
                        <td><%=user.getRegistered().format(TimeUtil.format)%>
                        </td>
                        <td><a><span class="fa fa-pencil"></span></a></td>
                        <td><a><span class="fa fa-remove"></span></a></td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>