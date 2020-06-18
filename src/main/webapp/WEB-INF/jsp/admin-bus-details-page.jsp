<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.content" var="bundle"/>
<c:set var="bus" value="${bus}" scope="request"/>
<c:set var="busId" value="${bus.id}" scope="request"/>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

        <title><fmt:message key="title.admin.buses.page" bundle="${bundle}"/></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp" >
            <jsp:param name="command" value="get_bus_details" />
            <jsp:param name="successMessage" value="${successMessage}" />
            <jsp:param name="backButton" value="true" />
        </jsp:include>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="${successMessage}" bundle="${bundle}"/>
            </div>
        </c:if>

        <center><h4><fmt:message key="header.admin.bus" bundle="${bundle}"/></h4></center></br>
        <div class="mx-auto" style="width: 300px">
            <div class="form-group">
                <label for="input-creationTime"><fmt:message key="label.bus.creation.time" bundle="${bundle}"/></label>
                <fmt:parseDate value="${bus.creationTime}" type="both" pattern="yyyy-MM-dd'T'HH:mm" var="busCreationTime"/>
                <fmt:formatDate value="${busCreationTime}" type="both" dateStyle="short" timeStyle="short" var="parsedCreationTime"/>
                <input id="input-creationTime" class="form-control" type="text" disabled="true" value="${parsedCreationTime}"/>
            </div>
        </div>
        <jsp:include page="components/bus-form.jsp" >
            <jsp:param name="command" value="edit_bus" />
        </jsp:include>
        </br>
    </body>
</html>
