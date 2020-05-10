<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>bus creation</title>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="i18n.content" var="bundle"/>
</head>
    <body>
        <jsp:include page="logout.jsp"/>
        <h1><fmt:message key="bus.add.page.title" bundle="${bundle}"/></h1>
        <form name="bus-creation" action="./controller" method="post">
            <p>
                <fmt:message key="bus.number.label" bundle="${bundle}"/>
                <input name="number" type="text">                               <!--ADD VALIDATION-->
                <fmt:message key="bus.model.label" bundle="${bundle}"/>
                <input name="model" type="text">                                <!--ADD VALIDATION-->
                <fmt:message key="bus.capacity.label" bundle="${bundle}"/>
                <input name="passengersCapacity" type="text">                   <!--ADD VALIDATION-->
                <fmt:message key="bus.mileage.label" bundle="${bundle}"/>
                <input name="mileage" type="text">                              <!--ADD VALIDATION-->
                <fmt:message key="bus.colour.en.label" bundle="${bundle}"/>
                <input name="colourEn" type="text">                             <!--ADD VALIDATION-->
                <fmt:message key="bus.colour.ua.label" bundle="${bundle}"/>
                <input name="colourUa" type="text">                             <!--ADD VALIDATION-->
                <fmt:message key="bus.notes.en.label" bundle="${bundle}"/>
                <input name="notesEn" type="text">                              <!--ADD VALIDATION-->
                <fmt:message key="bus.notes.ua.label" bundle="${bundle}"/>
                <input name="notesUa" type="text">                              <!--ADD VALIDATION-->
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
