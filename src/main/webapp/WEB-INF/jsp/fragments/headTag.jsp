<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<head>
    <!-- Required meta tags -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="app.title"/></title>
    <base href="${pageContext.request.contextPath}/"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <link rel="icon" type="image/png" href="resources/images/favicon.ico"/>

    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="webjars/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/noty/3.1.4/demo/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.21/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="webjars/noty/3.1.4/lib/noty.css"/>

    <%--http://stackoverflow.com/a/24070373/548473--%>
    <script type="text/javascript" src="webjars/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.5.0/js/bootstrap.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.21/js/jquery.dataTables.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.21/js/dataTables.bootstrap4.min.js" defer></script>
    <script type="text/javascript" src="webjars/noty/3.1.4/lib/noty.min.js" defer></script>
</head>
