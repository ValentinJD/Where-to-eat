<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-light bg-light navbar-fixed-bottom p-0">
    <div class="container">
        <div class="col-4">
            <a href="restaurants" class="navbar-brand pull-left"><img class="img-fluid" width="70"
                                                                      src="resources/images/logon.png"></a>
        </div>
        <div class="col-5">
            <a href="restaurants"><H5 CLASS="text-primary"><spring:message code="app.title"/></H5></a>
        </div>
        <div class="col-3 p-3">
            <sec:authorize access="isAuthenticated()">
                <form class="form-inline my-2">
                    <sec:authorize access="hasRole('ADMIN')">
                        <a class="btn btn-info mr-1" href="users"><spring:message code="user.title"/></a>
                    </sec:authorize>
                    <a class="btn btn-primary my-1" href="logout">
                        <span class="fa fa-sign-out"></span>
                    </a>
                </form>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <form class="form-inline my-2" id="login_form" action="spring_security_check" method="post">
                    <input class="form-control mr-1" type="text" placeholder="Email" name="username">
                    <input class="form-control mr-1" type="password" placeholder="Password" name="password">
                    <button class="btn btn-success" type="submit">
                        <span class="fa fa-sign-in"></span>
                    </button>
                </form>
            </sec:authorize>
        </div>
    </div>
</nav>

