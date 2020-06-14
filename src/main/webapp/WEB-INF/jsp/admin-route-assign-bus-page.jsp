<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.content" var="bundle"/>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

        <title><fmt:message key="title.admin.routes.page" bundle="${bundle}"/></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp" >
            <jsp:param name="command" value="get_unassigned_buses" />
            <jsp:param name="additionalParameterName" value="routeId" />
            <jsp:param name="additionalParameterValue" value="${routeId}" />
            <jsp:param name="backButton" value="true" />
        </jsp:include>

        <center><h4><fmt:message key="header.admin.route.assign.bus" bundle="${bundle}"/></h4></center></br>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="label.bus.number" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.bus.capacity" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.bus.notes" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.bus.assign" bundle="${bundle}"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${buses}" var="bus" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.count}</th>
                        <td>${bus.number}</td>
                        <td>${bus.passengersCapacity}</td>
                        <td>${bus.localizedNotes[sessionScope.locale]}</td>
                        <td>
                            <form name="assign-bus" action="./controller" method="post">
                                <input type="hidden" name="routeId" value="${routeId}">
                                <input type="hidden" name="busId" value="${bus.id}">
                                <input type="hidden" name="command" value="create_bus_assignment">
                                <button class="btn btn-primary"><fmt:message key="button.assign" bundle="${bundle}"/></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
