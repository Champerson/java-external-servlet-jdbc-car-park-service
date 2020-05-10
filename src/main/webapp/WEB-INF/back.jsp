<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>back</title>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="i18n.content" var="bundle"/>
</head>
    <body>
        <form name="back" action="./controller" method="post">
            <input name="command" type="hidden" value="redirect">
            <input name="pageToRedirect" type="hidden" value="WEB-INF/admin-menu.jsp">
            <button><fmt:message key="back.button" bundle="${bundle}"/></button>
        </form>
    </body>
</html>
