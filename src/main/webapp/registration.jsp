<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>registration page</title>
        <fmt:requestEncoding value="UTF-8" />
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="i18n.content" var="bundle"/>
    </head>
    <body>
        <h1><fmt:message key="registration.page.title" bundle="${bundle}"/></h1>
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
        <form name="test" action="${localeFormAction}" method="${localeFormMethod}">
            ${localeFormCommand}
            <input name="locale" type="hidden" value="en_EN">
            <button>English</button>
        </form>
        <form name="test" action="${localeFormAction}" method="${localeFormMethod}">
            ${localeFormCommand}
            <input name="locale" type="hidden" value="uk_UA">
            <button>Українська</button>
        </form>
        <form name="registration" action="./controller" method="post">
            <p>
                <b><fmt:message key="login.label" bundle="${bundle}"/></b><br>
                <c:if test="${not empty validationErrors.login}">
                    <fmt:message key="${validationErrors.login}" bundle="${bundle}"/>
                </c:if>
                <input type="text" name="login">
            </p>
            <p>
                <b><fmt:message key="password.label" bundle="${bundle}"/></b><br>
                <c:if test="${not empty validationErrors.password}">
                    <fmt:message key="${validationErrors.password}" bundle="${bundle}"/>
                </c:if>
                <input type="password" name="password">
            </p>
            <p>
                <b><fmt:message key="name.label" bundle="${bundle}"/></b><br>
                <c:if test="${not empty validationErrors.name}">
                    <fmt:message key="${validationErrors.name}" bundle="${bundle}"/>
                </c:if>
                <input type="text" name="name">
            </p>
            <p>
                <b><fmt:message key="email.label" bundle="${bundle}"/></b><br>
                <c:if test="${not empty validationErrors.email}">
                    <fmt:message key="${validationErrors.email}" bundle="${bundle}"/>
                </c:if>
                <input type="text" name="email">
            </p>
            <p>
                <b><fmt:message key="phone.label" bundle="${bundle}"/></b><br>
                <c:if test="${not empty validationErrors.phone}">
                    <fmt:message key="${validationErrors.phone}" bundle="${bundle}"/>
                </c:if>
                <input type="text" name="phone">
            </p>
            <p>
                <b><fmt:message key="age.label" bundle="${bundle}"/></b><br>
                <c:if test="${not empty validationErrors.age}">
                    <fmt:message key="${validationErrors.age}" bundle="${bundle}"/>
                </c:if>
                <input type="text" name="age">
            </p>
            <p>
                <input type="submit" value="<fmt:message key="submit.registration.button" bundle="${bundle}"/>">
                <input type="reset" value="<fmt:message key="reset.button" bundle="${bundle}"/>">
            </p>
            <input name="command" type="hidden" value="create_user">
        </form>
    </body>
</html>