<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<section>
    <b>User of&nbsp;</b>
    <form method="post" action="users?login=yes">
        <fmt:message key="app.login"/>:
        <select name="userId">
            <option value="100001" selected>User</option>
            <option value="100000">Admin</option>
        </select>
        <button type="submit"><fmt:message key="common.select"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>