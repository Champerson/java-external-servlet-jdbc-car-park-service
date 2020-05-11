<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>route creation</title>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="i18n.content" var="bundle"/>
</head>
    <body>
        <jsp:include page="logout.jsp"/>
        <h1><fmt:message key="route.add.page.title" bundle="${bundle}"/></h1>
        <form name="create-route" action="./controller" method="post">
            <p>
                <fmt:message key="route.number.label" bundle="${bundle}"/><br>
                <c:if test="${not empty validationErrors.number}">
                    <fmt:message key="${validationErrors.number}" bundle="${bundle}"/><br>
                </c:if>
                <input type="text" name="number">
            </p>
            <p>
                <fmt:message key="route.length.label" bundle="${bundle}"/><br>
                <c:if test="${not empty validationErrors.length}">
                    <fmt:message key="${validationErrors.length}" bundle="${bundle}"/><br>
                </c:if>
                <input type="text" name="length">
            </p>
            <p>
                <fmt:message key="route.description.en.label" bundle="${bundle}"/><br>
                <c:if test="${not empty validationErrors.descriptionEn}">
                    <fmt:message key="${validationErrors.descriptionEn}" bundle="${bundle}"/><br>
                </c:if>
                <input type="text" name="descriptionEn">
            </p>
            <p>
                <fmt:message key="route.description.ua.label" bundle="${bundle}"/><br>
                <c:if test="${not empty validationErrors.descriptionUa}">
                    <fmt:message key="${validationErrors.descriptionUa}" bundle="${bundle}"/><br>
                </c:if>
                <input type="text" name="descriptionUa">
            </p>
            <p>
                <input name="command" type="hidden" value="create_route">
                <input type="submit" value="<fmt:message key="submit.button" bundle="${bundle}"/>">
                <input type="reset" value="<fmt:message key="reset.button" bundle="${bundle}"/>">
            </p>
        </form>
        <br><jsp:include page="back.jsp"/>
    </body>
</html>
