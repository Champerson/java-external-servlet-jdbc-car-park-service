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
            <jsp:param name="command" value="get_unassigned_drivers" />
            <jsp:param name="additionalParameterName" value="routeId" />
            <jsp:param name="additionalParameterValue" value="${routeId}" />
            <jsp:param name="backButton" value="true" />
        </jsp:include>

        <center><h4><fmt:message key="header.admin.route.assign.driver" bundle="${bundle}"/></h4></center></br>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="label.user.login" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.user.name" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.user.phone" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.user.email" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.user.assign" bundle="${bundle}"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.count}</th>
                        <td>${user.login}</td>
                        <td>${user.name}</td>
                        <td>${user.phone}</td>
                        <td>${user.email}</td>
                        <td>
                            <form name="assign-driver" action="./controller" method="post">
                                <input name="routeId" type="hidden" value="${routeId}">
                                <input name="assignmentId" type="hidden" value="${assignmentId}">
                                <input name="userId" type="hidden" value="${user.id}">
                                <input name="command" type="hidden" value="create_driver_assignment">
                                <button class="btn btn-primary"><fmt:message key="button.assign" bundle="${bundle}"/></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
