<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            <jsp:param name="command" value="get_all_routes" />
            <jsp:param name="successMessage" value="${successMessage}" />
            <jsp:param name="backButton" value="true" />
        </jsp:include>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="${successMessage}" bundle="${bundle}"/>
            </div>
        </c:if>

        <center><h4><fmt:message key="header.admin.all.routes.page" bundle="${bundle}"/></h4></center></br>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="label.route.number" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.route.description" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.route.assigned.buses.quantity" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.route.open.details" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.route.delete" bundle="${bundle}"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${routes}" var="route" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.count}</th>
                        <td>${route.number}</td>
                        <td>${route.localizedDescription[sessionScope.locale]}</td>
                        <td>${route.assignments.size()}</td>
                        <td>
                            <form name="edit-route" action="./controller" method="post">
                                <input name="command" type="hidden" value="get_route_details">
                                <input name="routeId" type="hidden" value="${route.id}">
                                <button class="btn btn-primary"><fmt:message key="button.details" bundle="${bundle}"/></button>
                            </form>
                        </td>
                        <td>
                            <form name="delete-route" action="./controller" method="post">
                                <input name="command" type="hidden" value="delete_route">
                                <input name="routeId" type="hidden" value="${route.id}">
                                <button class="btn btn-danger">X</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="mx-auto" style="width: 300px">
            <form name="add-new-route" action="./controller" method="post">
                <input name="command" type="hidden" value="redirect">
                <input name="pageToRedirect" type="hidden" value="WEB-INF/jsp/admin-route-create-page.jsp">
                <button class="btn btn-success" style="width: 300px"><fmt:message key="button.route.create" bundle="${bundle}"/></button>
            </form>
        </div>
    </body>
</html>