<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>locale</title>
        <fmt:requestEncoding value="UTF-8" />
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="i18n.content" var="bundle"/>
    </head>

    <body>
        <form name="test" action="./controller" method="post">
            <input name="command" type="hidden" value="${command}">
            <input name="locale" type="hidden" value="en_EN">
            <button><fmt:message key="locale.en.button" bundle="${bundle}"/></button>
        </form>

        <form name="test" action="./controller" method="post">
            <input name="command" type="hidden" value="${command}">
            <input name="locale" type="hidden" value="uk_UA">
            <button><fmt:message key="locale.ua.button" bundle="${bundle}"/></button>
        </form>
    </body>
</html>
