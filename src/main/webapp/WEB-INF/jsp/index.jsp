<%@ page import="ru.whereToEat.web.SecurityUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<jsp:include page="fragments/headTag.jsp"/>
<html>
<body class="main">

<div class="main op">
    <jsp:include page="fragments/bodyHeader.jsp"/>
    <p class="restaurant"><spring:message code="user.name"/>: <%=SecurityUtil.getUserName() %>
    </p>
    <br>
    <div class="restaurant">
        <section>
            <form method="post" action="users?login=yes">
                <spring:message code="app.login"/>:
                <select name="userId">
                    <option value="100001" selected>User</option>
                    <option value="100000">Admin</option>
                </select>
                <button type="submit"><spring:message code="common.select"/></button>
            </form>
        </section>
    </div>


    <jsp:include page="fragments/footer.jsp"/>
</div>

</body>
</html>