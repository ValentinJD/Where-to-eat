<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="jumbotron py-0">

    <nav class="navbar navbar-expand-md navbar-light bg-light p-0">
        <div class="container">
            <div class="col-2">
                <a href="restaurants" class="navbar-brand pull-left"><img class="img-fluid" width="70"
                                                                          src="resources/images/logon.png"></a>
            </div>
            <div class="col-3 m-2">
                <a href="restaurants"><b><spring:message code="app.title"/></b></a>
            </div>
            <div class="col-6 m-2">
                <sec:authorize access="isAuthenticated()">
                    <form:form class="float-right form-inline my-1" action="logout" method="post">
                        <sec:authorize access="hasRole('ADMIN')">
                            <a class="btn btn-info mr-1" href="users"><spring:message code="user.title"/></a>
                        </sec:authorize>
                        <a class="btn btn-info mr-1" href="profile"><sec:authentication
                                property="principal.userTo.name"/> <spring:message
                                code="app.profile"/></a>
                        <button class="btn btn-primary my-1" type="submit">
                            <span class="fa fa-sign-out"></span>
                        </button>
                    </form:form>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <form:form class="float-right form-inline my-2" id="login_form" action="spring_security_check"
                               method="post">
                        <input class="form-control mr-1" type="text" placeholder="Email" name="username">
                        <input class="form-control mr-1" type="password" placeholder="Password" name="password">
                        <button class="btn btn-success" type="submit">
                            <span class="fa fa-sign-in"></span>
                        </button>
                    </form:form>
                </sec:authorize>
            </div>
            <div class="col-1 collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                        <a class="dropdown-toggle nav-link my-1 ml-2"
                           data-toggle="dropdown">${pageContext.response.locale}</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item"
                               href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a>
                            <a class="dropdown-item"
                               href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <br>
</div>
<script type="text/javascript">
    var localeCode = "${pageContext.response.locale}";
</script>

