<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
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
            <dd><input type="checkbox" value="${user.enabled}" name="enabled" required></dd>
        </dl>
        <dl>
            <dt>Registered:</dt>
            <dd><input type="datetime-local" value="${user.registered}" name="registered" required></dd>
        </dl>
        <dl>
            <dt>Role:</dt>
            <dd><input type="text" value="${user.role}" size=40 name="role" required></dd>
        </dl>

        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
