<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <fmt:requestEncoding value="UTF-8" />
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="i18n.content" var="bundle"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in</title>
        </head>
    <body>
        <h1><fmt:message key="main.page.title" bundle="${bundle}"/></h1>
        <form name="authorization" action="./controller" method="post">
            <p>
                <b><fmt:message key="login.label" bundle="${bundle}"/></b><br> <!--ADD VALIDATION-->
                <input type="text" name="login">
            </p>
            <p>
                <b><fmt:message key="password.label" bundle="${bundle}"/></b><br> <!--ADD VALIDATION-->
                <input type="password" name="password">
            </p>
            <p>
                <input type="submit" value="<fmt:message key="submit.log.in.button" bundle="${bundle}"/>">
                <input type="reset" value="<fmt:message key="reset.button" bundle="${bundle}"/>">
            </p>
            <input name="command" type="hidden" value="authorization">
        </form>
        <a href="registration.jsp"><fmt:message key="registration.link" bundle="${bundle}"/></a>
    </body>
</html>