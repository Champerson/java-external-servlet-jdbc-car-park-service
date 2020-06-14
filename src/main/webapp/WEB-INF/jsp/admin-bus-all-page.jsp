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

        <title><fmt:message key="title.admin.buses.page" bundle="${bundle}"/></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp" >
            <jsp:param name="command" value="get_all_buses" />
            <jsp:param name="backButton" value="true" />
            <jsp:param name="successMessage" value="${successMessage}" />
        </jsp:include>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="${successMessage}" bundle="${bundle}"/>
            </div>
        </c:if>

        <center><h4><fmt:message key="header.admin.all.buses.page" bundle="${bundle}"/></h4></center></br>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="label.bus.number" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.bus.capacity" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.bus.notes" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.bus.assigned.route" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.bus.assigned.driver" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.bus.open.details" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.bus.delete" bundle="${bundle}"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${buses}" var="bus" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.count}</th>
                        <td>${bus.number}</td>
                        <td>${bus.passengersCapacity}</td>
                        <td>${bus.localizedNotes[sessionScope.locale]}</td>
                        <td>${bus.assignment.route.number}</td>
                        <td>${bus.assignment.driver.name}</td>
                        <td>
                            <form name="edit-bus" action="./controller" method="post">
                                <input name="command" type="hidden" value="get_bus_details">
                                <input name="busId" type="hidden" value="${bus.id}">
                                <button class="btn btn-primary"><fmt:message key="button.details" bundle="${bundle}"/></button>
                            </form>
                        </td>
                        <td>
                            <form name="delete-bus" action="./controller" method="post">
                                <input name="command" type="hidden" value="delete_bus">
                                <input name="busId" type="hidden" value="${bus.id}">
                                <button class="btn btn-danger">X</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="mx-auto" style="width: 300px">
            <form name="add-new-bus" action="./controller" method="post">
                <input name="command" type="hidden" value="redirect">
                <input name="pageToRedirect" type="hidden" value="WEB-INF/jsp/admin-bus-create-page.jsp">
                <button class="btn btn-success" style="width: 300px"><fmt:message key="button.bus.create" bundle="${bundle}"/></button>
            </form>
        </div>
    </body>
</html>
