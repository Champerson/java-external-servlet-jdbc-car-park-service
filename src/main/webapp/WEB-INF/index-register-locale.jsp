<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title>index-register-locale</title>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="i18n.content" var="bundle"/>
</head>
        <body>
            <c:choose>
                <c:when test="${not empty command}">
                    <c:set var="localeFormAction" value="./controller"/>
                    <c:set var="localeFormMethod" value="post"/>
                    <c:set var="localeFormCommand" value="<input name='command' type='hidden' value='${command}'>"/>
                </c:when>
                <c:otherwise>
                    <c:set var="localeFormAction" value="${pageContext.request.requestURL}"/>
                    <c:set var="localeFormMethod" value="get"/>
                    <c:set var="localeFormCommand" value=""/>
                </c:otherwise>
            </c:choose>
            <form name="index-register-locale" action="${localeFormAction}" method="${localeFormMethod}">
                ${localeFormCommand}
                <input name="locale" type="hidden" value="en_EN">
                <button>English</button>
            </form>
            <form name="index-register-locale" action="${localeFormAction}" method="${localeFormMethod}">
                ${localeFormCommand}
                <input name="locale" type="hidden" value="uk_UA">
                <button>Українська</button>
            </form>
        </body>
</html>
