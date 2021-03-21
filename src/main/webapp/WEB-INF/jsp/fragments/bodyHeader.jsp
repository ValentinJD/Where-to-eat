<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-light bg-light navbar-fixed-bottom p-0">
    <div class="container-fluid p-0">


        <div class="col-3">
            <a href="restaurants" class="navbar-brand pull-left"><img class="img-fluid" width="70"
                                                                      src="resources/images/logon.png"></a>
        </div>
        <div class="col-6">
            <a href="restaurants"><H5 CLASS="text-primary"><spring:message code="app.title"/></H5></a>
        </div>
        <div class="col-3 p-3">
            <a class="btn btn-info mr-1" href="users"><spring:message code="user.title"/></a>
            <a class="btn btn-primary" href="logout">
                <span class="fa fa-sign-out"></span>
            </a>
        </div>


    </div>
</nav>

