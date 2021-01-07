<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ page import="ru.whereToEat.web.SecurityUtil" %><%--<%@ page import="ru.whereToEat.util.TimeUtil" %>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/headTag.jsp"/>
<html>

<body class="main">
<div>
    <jsp:include page="fragments/bodyHeader.jsp"/>
    <p class="restaurant"><spring:message code="user.name"/> : <%=SecurityUtil.getUserName() %>
    </p>
    <div class="restaurant  op">
        <h3><spring:message code="user.title"/></h3>

        <section>
            <table border="1" cellpadding="8" cellspacing="0" align="center">
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
                        <td>${user.id}</td>
                        <td><c:out value="${user.name}"/></td>
                        <td><a href="mailto:${user.email}">${user.email}</a></td>
                        <td>${user.role}</td>
                        <td><%=user.isEnabled()%>
                        </td>
                        <td><%=user.getRegistered().format(TimeUtil.format)%>
                        </td>
                        <td><a href="users?action=update&userId=${user.id}" class="c"><spring:message code="user.edit"/></a>
                        </td>
                        <td><a href="users?action=delete&userId=${user.id}" class="c"><spring:message
                                code="user.delete"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br><a href="users?action=create" class="c"><spring:message code="user.create"/></a>
        </section>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>