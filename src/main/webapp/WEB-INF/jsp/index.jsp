<%@ page import="ru.whereToEat.web.SecurityUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="fragments/headTag.jsp"/>

<html>
<body>

<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron container pt-4">
    <div class="container">
        <p><spring:message code="user.name"/>: <%=SecurityUtil.getUserName() %>
        </p>
        <br>
        <div class="card h-50">
            <div class="card-body">
                <section class="h-75 w-50">
                    <form class="form-inline" method="post" action="users?login=yes">
                        <spring:message code="app.login"/>:
                        <select name="userId">
                            <option value="100001" selected>User</option>
                            <option value="100000">Admin</option>
                        </select>
                        <button class="btn btn-primary" type="submit"><spring:message code="common.select"/></button>
                    </form>
                </section>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

</body>
</html>