<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>locale</title>
        <fmt:requestEncoding value="UTF-8" />
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="i18n.content" var="bundle"/>
    </head>

    <body>
        <form name="locale" action="./controller" method="post">
            <input name="command" type="hidden" value="${command}">
            <input name="locale" type="hidden" value="en_EN">
            <button><fmt:message key="locale.en.button" bundle="${bundle}"/></button>
        </form>

        <form name="locale" action="./controller" method="post">
            <input name="command" type="hidden" value="${command}">
            <input name="locale" type="hidden" value="uk_UA">
            <button><fmt:message key="locale.ua.button" bundle="${bundle}"/></button>
        </form>
    </body>
</html>
