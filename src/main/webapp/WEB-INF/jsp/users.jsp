<%@ page import="ru.whereToEat.util.TimeUtil" %><%--<%@ page import="ru.whereToEat.util.TimeUtil" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="messages.app"/>
<jsp:include page="fragments/headTag.jsp"/>
<html>


<body class="main">
<div class="restaurant">
    <jsp:include page="fragments/bodyHeader.jsp"/>
</div>

<div class="restaurant">
<h3><fmt:message key="user.title"/></h3>
<section>
    <table border="1" cellpadding="8" cellspacing="0" align="center">
        <thead>
        <tr>
            <th><fmt:message key="user.id"/></th>
            <th><fmt:message key="user.name"/></th>
            <th><fmt:message key="user.email"/></th>
            <th><fmt:message key="user.roles"/></th>
            <th><fmt:message key="user.active"/></th>
            <th><fmt:message key="user.registered"/></th>
            <th colspan="2"><fmt:message key="user.action"/></th>
        </tr>
        </thead>

        <c:forEach items="${users}" var="user">

            <jsp:useBean id="user" scope="page" type="ru.whereToEat.model.User"/>

            <tr>
                <td>${user.id}</td>
                <td><c:out value="${user.name}"/></td>
                <td><a href="mailto:${user.email}">${user.email}</a></td>
                <td>${user.role}</td>
                <td><%=user.isEnabled()%>
                </td>
                    <%--                <td><fmt:formatDate value="${user.registered}" pattern="dd-MM-yyyy"/></td>--%>

                <td><%=user.getRegistered().format(TimeUtil.format)%>
                </td>
                <td><a href="users?action=update&userId=${user.id}" class="c"><fmt:message key="user.edit"/></a></td>
                <td><a href="users?action=delete&userId=${user.id}" class="c"><fmt:message key="user.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
    <br><a href="users?action=create">Создать</a>
</section>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>