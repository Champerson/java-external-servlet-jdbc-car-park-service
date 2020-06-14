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

        <title><fmt:message key="title.admin.users.page" bundle="${bundle}"/></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp" >
            <jsp:param name="command" value="get_all_users" />
            <jsp:param name="backButton" value="true" />
            <jsp:param name="successMessage" value="${successMessage}" />
        </jsp:include>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="${successMessage}" bundle="${bundle}"/>
            </div>
        </c:if>

        <center><h4><fmt:message key="header.admin.all.users.page" bundle="${bundle}"/></h4></center></br>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="label.user.login" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.user.name" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.user.role" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.user.assigned.route" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.user.open.details" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="label.user.delete" bundle="${bundle}"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.count}</th>
                        <td>${user.login}</td>
                        <td>${user.name}</td>
                        <td>
                            <c:if test="${user.accessRole == 'ROLE_DRIVER'}"><fmt:message key="label.user.role.driver" bundle="${bundle}"/></c:if>
                            <c:if test="${user.accessRole == 'ROLE_ADMIN'}"><fmt:message key="label.user.role.admin" bundle="${bundle}"/></c:if>
                        </td>
                        <td>${user.assignment.route.number}</td>
                        <td>
                            <form name="user-details" action="./controller" method="post">
                                <input name="command" type="hidden" value="get_user_details">
                                <input name="userId" type="hidden" value="${user.id}">
                                <button class="btn btn-primary"><fmt:message key="button.details" bundle="${bundle}"/></button>
                            </form>
                        </td>
                        <td>
                            <form name="delete-user" action="./controller" method="post">
                                <input name="command" type="hidden" value="delete_user">
                                <input name="userId" type="hidden" value="${user.id}">
                                <button class="btn btn-danger">X</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
