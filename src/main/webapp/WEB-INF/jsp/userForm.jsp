<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <dt>Name:</dt>
                <dd><input type="text" value="${user.name}" size=40 name="name" required></dd>
            </dl>
            <dl>
                <dt>Email:</dt>
                <dd><input type="text" value="${user.email}" size=40 name="email" required></dd>
            </dl>
            <dl>
                <dt>Password:</dt>
                <dd><input type="text" value="${user.password}" size=40 name="password" required></dd>
            </dl>
            <dl>
                <dt>Enabled:</dt>
                <dd><input type="checkbox" value="${user.enabled}" name="enabled" checked></dd>
            </dl>
            <dl>
                <dt>Registered:</dt>
                <dd><input type="datetime-local" value="${user.registered}" name="registered"></dd>
            </dl>
            <dl>
                <dt>Role:</dt>
                <dd>
                    <select name="role" size="2" multiple required>
                        <option selected value="ADMIN">ADMIN</option>
                        <option value="USER">USER</option>
                    </select>
                </dd>
            </dl>

            <button type="submit">Save</button>
            <button onclick="window.history.back()" type="button">Cancel</button>
        </form>
    </section>
</div>
</body>
</html>
