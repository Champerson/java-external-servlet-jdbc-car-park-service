<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.content" var="bundle"/>
<c:set var="route" value="${route}" scope="request"/>
<c:set var="routeId" value="${route.id}" scope="request"/>

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
            <jsp:param name="command" value="get_route_details" />
            <jsp:param name="successMessage" value="${successMessage}" />
            <jsp:param name="backButton" value="true" />
        </jsp:include>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="${successMessage}" bundle="${bundle}"/>
            </div>
        </c:if>

        <center><h4><fmt:message key="header.admin.route" bundle="${bundle}"/></h4></center></br>
        <center><table>
            <tr>
                <td>
                    <div class="card">
                        <div class="card-body">
                            <div class="mx-auto" style="width: 300px">
                                <div class="form-group">
                                    <label for="input-creationTime"><fmt:message key="label.route.creation.time" bundle="${bundle}"/></label>
                                    <fmt:parseDate value="${route.creationTime}" type="both" pattern="yyyy-MM-dd'T'HH:mm" var="routeCreationTime"/>
                                    <fmt:formatDate value="${routeCreationTime}" type="both" dateStyle="short" timeStyle="short" var="parsedCreationTime"/>
                                    <input id="input-creationTime" class="form-control" type="text" disabled="true" value="${parsedCreationTime}"/>
                                </div>
                            </div>
                            <jsp:include page="components/route-form.jsp" >
                                <jsp:param name="command" value="edit_route" />
                            </jsp:include>
                        </div>
                    </div>
                </td>
                <td style="width:30px"></td>
                <td>
                    <c:if test="${empty route.assignments}">
                        <center><h5><fmt:message key="label.route.no.assigned.buses" bundle="${bundle}"/></h5></center>
                    </c:if>
                    <c:if test="${not empty route.assignments}">
                        <center><h5><fmt:message key="label.route.assigned.buses" bundle="${bundle}"/></h5></center>
                        <table class="table mx-auto table-bordered" style="width: 800px">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col"><fmt:message key="label.bus.number" bundle="${bundle}"/></th>
                                    <th scope="col"><fmt:message key="label.bus.assigned.driver" bundle="${bundle}"/></th>
                                    <th scope="col"><fmt:message key="label.route.assignment.decline" bundle="${bundle}"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${route.assignments}" var="assignment" varStatus="loop">
                                    <tr>
                                        <th scope="row" style="vertical-align: middle">${loop.count}</th>
                                        <td style="vertical-align: middle">${assignment.bus.number}</td>
                                        <td style="vertical-align: middle">
                                            <c:choose>
                                                <c:when test="${empty assignment.driver}">
                                                    <form name="route-assign-driver" action="./controller" method="post">
                                                        <input type="hidden" name="command" value="get_unassigned_drivers">
                                                        <input type="hidden" name="busId" value="${assignment.bus.id}">
                                                        <input type="hidden" name="routeId" value="${route.id}">
                                                        <input type="hidden" name="assignmentId" value="${assignment.id}">
                                                        <button class="btn btn-success"><fmt:message key="button.route.assign.driver" bundle="${bundle}"/></button>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <table>
                                                        <tr>
                                                            <td style="border:none">
                                                                ${assignment.driver.name}</br>
                                                                <c:choose>
                                                                    <c:when test="${assignment.acceptedByDriver eq true}">
                                                                        <font color="green"><fmt:message key="label.route.assignment.confirmed.by.user" bundle="${bundle}"/></font>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <font color="red"><fmt:message key="label.route.assignment.waiting.confirmation" bundle="${bundle}"/></font>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td style="border:none;vertical-align: middle">
                                                                <form name="decline-driver-assignment" action="./controller" method="post">
                                                                    <input type="hidden" name="command" value="edit_user_assignment_delete">
                                                                    <input type="hidden" name="nextCommand" value="get_route_details">
                                                                    <input type="hidden" name="assignmentId" value="${assignment.id}">
                                                                    <input type="hidden" name="routeId" value="${route.id}">
                                                                    <button class="btn btn-secondary"><fmt:message key="button.decline" bundle="${bundle}"/></button>
                                                                </form>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td style="vertical-align: middle">
                                            <form name="delete-bus-assignment" action="./controller" method="post">
                                                <input type="hidden" name="command" value="delete_assignment">
                                                <input type="hidden" name="assignmentId" value="${assignment.id}">
                                                <input type="hidden" name="routeId" value="${route.id}">
                                                <button class="btn btn-secondary"><fmt:message key="button.decline.bus" bundle="${bundle}"/></button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <div class="mx-auto" style="width: 300px">
                        <form name="add-bus" action="./controller" method="post">
                            <input name="command" type="hidden" value="get_unassigned_buses">
                            <input name="routeId" type="hidden" value="${route.id}">
                            <button class="btn btn-success" style="width: 300px"><fmt:message key="button.route.add.bus" bundle="${bundle}"/></button>
                        </form>
                    </div>
                </td>
            </tr>
        </table></center>
    </body>
</html>
