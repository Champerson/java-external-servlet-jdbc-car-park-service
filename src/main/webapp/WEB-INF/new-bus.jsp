<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title>bus creation</title>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="i18n.content" var="bundle"/>
</head>
    <body>
        <jsp:include page="logout.jsp"/>
        <jsp:include page="locale.jsp"/>
        <h1><fmt:message key="bus.add.page.title" bundle="${bundle}"/></h1>
        <form name="bus-creation" action="./controller" method="post">
            <p>
                <fmt:message key="bus.number.label" bundle="${bundle}"/><br>
                <input name="number" type="text">
                <c:if test="${not empty validationErrors.number}">
                    <fmt:message key="${validationErrors.number}" bundle="${bundle}"/>
                </c:if><br>

                <fmt:message key="bus.model.label" bundle="${bundle}"/><br>
                <input name="model" type="text">
                <c:if test="${not empty validationErrors.model}">
                    <fmt:message key="${validationErrors.model}" bundle="${bundle}"/>
                </c:if><br>

                <fmt:message key="bus.capacity.label" bundle="${bundle}"/><br>
                <input name="passengersCapacity" type="text">
                <c:if test="${not empty validationErrors.passengersCapacity}">
                    <fmt:message key="${validationErrors.passengersCapacity}" bundle="${bundle}"/>
                </c:if><br>

                <fmt:message key="bus.mileage.label" bundle="${bundle}"/><br>
                <input name="mileage" type="text">
                <c:if test="${not empty validationErrors.mileage}">
                    <fmt:message key="${validationErrors.mileage}" bundle="${bundle}"/>
                </c:if><br>

                <fmt:message key="bus.colour.en.label" bundle="${bundle}"/><br>
                <input name="colourEn" type="text"><br>
                <c:if test="${not empty validationErrors.colourEn}">
                    <fmt:message key="${validationErrors.colourEn}" bundle="${bundle}"/>
                </c:if><br>

                <fmt:message key="bus.colour.ua.label" bundle="${bundle}"/><br>
                <input name="colourUa" type="text">
                <c:if test="${not empty validationErrors.colourUa}">
                    <fmt:message key="${validationErrors.colourUa}" bundle="${bundle}"/>
                </c:if><br>

                <fmt:message key="bus.notes.en.label" bundle="${bundle}"/><br>
                <input name="notesEn" type="text">
                <c:if test="${not empty validationErrors.notesEn}">
                    <fmt:message key="${validationErrors.notesEn}" bundle="${bundle}"/>
                </c:if><br>

                <fmt:message key="bus.notes.ua.label" bundle="${bundle}"/><br>
                <input name="notesUa" type="text">
                <c:if test="${not empty validationErrors.notesUa}">
                    <fmt:message key="${validationErrors.notesUa}" bundle="${bundle}"/>
                </c:if>
            </p>
            <p>
                <input type="submit" value="<fmt:message key="submit.button" bundle="${bundle}"/>">
                <input name="command" type="hidden" value="create_bus">
                <input type="reset" value="<fmt:message key="reset.button" bundle="${bundle}"/>">
            </p>
        </form>
        <br><jsp:include page="back.jsp"/>
    </body>
</html>
