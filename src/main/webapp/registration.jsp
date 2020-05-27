<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>registration page</title>
        <fmt:requestEncoding value="UTF-8" />
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="i18n.content" var="bundle"/>
    </head>
    <body>
        <jsp:include page="WEB-INF/index-register-locale.jsp"/>
        <h1><fmt:message key="registration.page.title" bundle="${bundle}"/></h1>
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