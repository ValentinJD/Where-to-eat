<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="messages.app"/>
<jsp:include page="fragments/headTag.jsp"/>

<html>

<body class="main">

<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="restaurant">
    <section>
        <h2>${param.action == 'create' ? 'Create user' : 'Edit user'}</h2>
        <jsp:useBean id="user" type="ru.whereToEat.model.User" scope="request"/>
        <form method="post" action="users">
            <input type="hidden" name="userId" value="${user.id}">
            <dl>
                <dt><fmt:message key="user.name"/>:</dt>
                <dd><input type="text" value="${user.name}" size=40 name="name" required></dd>
            </dl>
            <dl>
                <dt><fmt:message key="user.email"/>:</dt>
                <dd><input type="text" value="${user.email}" size=40 name="email" required></dd>
            </dl>
            <dl>
                <dt><fmt:message key="user.password"/>:</dt>
                <dd><input type="text" value="${user.password}" size=40 name="password" required></dd>
            </dl>
            <dl>
                <dt><fmt:message key="user.active"/>:</dt>
                <dd><input type="checkbox" value="${user.enabled}" name="enabled" checked></dd>
            </dl>
            <dl>
                <dt><fmt:message key="user.registered"/>:</dt>
                <dd><input type="datetime-local" value="${user.registered}" name="registered"></dd>
            </dl>
            <dl>
                <dt><fmt:message key="user.roles"/>:</dt>
                <dd>
                    <select name="role" size="2" multiple required>
                        <option selected value="ADMIN"><fmt:message key="user.admin"/></option>
                        <option value="USER"><fmt:message key="user.user"/></option>
                    </select>
                </dd>
            </dl>

            <button type="submit"><fmt:message key="common.save"/></button>
            <button onclick="window.history.back()" type="button"><fmt:message key="common.cancel"/></button>
        </form>
    </section>
</div>
</body>
</html>
