<%@ page import="ru.whereToEat.util.TimeUtil" %>
<%@ page import="ru.whereToEat.web.SecurityUtil" %><%--<%@ page import="ru.whereToEat.util.TimeUtil" %>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="fragments/headTag.jsp"/>
<html>
<body>
<script type="text/javascript" src="resources/js/vote.common.js" defer></script>
<script type="text/javascript" src="resources/js/vote.users.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div><p><spring:message code="user.name"/> : <%=SecurityUtil.getUserName() %>
</p>
    <div class="container bg-light">
        <h3><spring:message code="user.title"/></h3>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="user.create"/>
        </button>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <%--                <th><spring:message code="user.id"/></th>--%>
                <th><spring:message code="user.name"/></th>
                <th><spring:message code="user.email"/></th>
                <th><spring:message code="user.roles"/></th>
                <th><spring:message code="user.active"/></th>
                <th><spring:message code="user.registered"/></th>
                <%--                <th colspan="2"><spring:message code="user.action"/></th>--%>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${users}" var="user">
                <jsp:useBean id="user" type="ru.whereToEat.model.User"/>
                <tr data-userEnabled="${user.enabled}">
                        <%--                    <td><c:out value="${user.id}"/></td>--%>
                    <td><c:out value="${user.name}"/></td>
                    <td><a href="mailto:${user.email}">${user.email}</a></td>
                    <td>${user.role}</td>
                    <td><input type="checkbox" onclick="enable($(this), ${user.id})"
                               <c:if test="${user.enabled}">checked</c:if> id="${user.id}"/></td>
                    <td><%=user.getRegistered().format(TimeUtil.format)%>
                    </td>
                    <td><a class="update" id="${user.id}"><span class="fa fa-pencil"></span></a></td>
                    <td><a onclick="deleteRow(${user.id})"  class="delete" id="${user.id}"><span class="fa fa-remove"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="user.create"/></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="user.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="user.name"/>">
                    </div>

                    <div class="form-group">
                        <label for="email" class="col-form-label"><spring:message code="user.email"/></label>
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="<spring:message code="user.email"/>">
                    </div>

                    <div class="form-group">
                        <label for="password" class="col-form-label"><spring:message code="user.password"/></label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="<spring:message code="user.password"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>