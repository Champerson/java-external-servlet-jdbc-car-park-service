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
            <jsp:param name="command" value="get_user_details" />
            <jsp:param name="additionalParameterName" value="userId" />
            <jsp:param name="additionalParameterValue" value="${user.id}" />
            <jsp:param name="backButton" value="true" />
            <jsp:param name="successMessage" value="${successMessage}" />
        </jsp:include>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="${successMessage}" bundle="${bundle}"/>
            </div>
        </c:if>

        <center><h4><fmt:message key="header.admin.user" bundle="${bundle}"/></h4></center></br>
        <table class="table table-bordered mx-auto"  style="width: 600px">
            <tr>
                <td><fmt:message key="label.user.login" bundle="${bundle}"/></td>
                <td>${user.login}</td>
            </tr>
            <tr>
                <td><fmt:message key="label.user.name" bundle="${bundle}"/></td>
                <td>${user.name}</td>
            </tr>
            <tr>
                <td><fmt:message key="label.user.email" bundle="${bundle}"/></td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td><fmt:message key="label.user.phone" bundle="${bundle}"/></td>
                <td>${user.phone}</td>
            </tr>
            <tr>
                <td><fmt:message key="label.user.age" bundle="${bundle}"/></td>
                <td>${user.age}</td>
            </tr>
            <tr>
                <td><fmt:message key="label.user.creation.time" bundle="${bundle}"/></td>
                <td>
                    <fmt:parseDate value="${user.creationTime}" type="both" pattern="yyyy-MM-dd'T'HH:mm" var="userCreationTime"/>
                    <fmt:formatDate value="${userCreationTime}" type="both" dateStyle="short" timeStyle="short" var="parsedCreationTime"/>
                    ${parsedCreationTime}
                </td>
            </tr>
            <tr>
                <td style="vertical-align: middle"><fmt:message key="label.user.role" bundle="${bundle}"/></td>
                <td>
                    <form name="edit-user-role" action="./controller" method="post">
                        <input type="hidden" name="command" value="edit_user_role">
                        <input type="hidden" name="userId" value="${user.id}">
                        <table>
                            <tr>
                                <td style="border:none">
                                    <select class="form-control" name="accessRole" size="1">
                                        <option value="ROLE_DRIVER" <c:if test="${user.accessRole == 'ROLE_DRIVER'}">selected="selected"</c:if>>
                                            <fmt:message key="label.user.role.driver" bundle="${bundle}"/>
                                        </option>
                                        <option value="ROLE_ADMIN" <c:if test="${user.accessRole == 'ROLE_ADMIN'}">selected="selected"</c:if>>
                                            <fmt:message key="label.user.role.admin" bundle="${bundle}"/>
                                        </option>
                                    </select>
                                </td>
                                <td style="border:none">
                                    <button class="btn btn-success"><fmt:message key="button.save.changes" bundle="${bundle}"/></button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
            <c:if test="${not empty user.assignment}">
                <tr class="table-secondary">
                    <td style="vertical-align: middle"><fmt:message key="label.user.assignment" bundle="${bundle}"/></td>
                    <td style="vertical-align: middle">
                        <table>
                            <tr>
                                <td style="border:none;vertical-align: middle">
                                    <c:choose>
                                        <c:when test="${user.assignment.acceptedByDriver eq true}">
                                            <font color="green"><fmt:message key="label.route.assignment.confirmed.by.user" bundle="${bundle}"/></font>
                                        </c:when>
                                        <c:otherwise>
                                            <font color="red"><fmt:message key="label.route.assignment.waiting.confirmation" bundle="${bundle}"/></font>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td style="border:none">
                                    <form name="decline-driver-assignment" action="./controller" method="post">
                                        <input type="hidden" name="command" value="edit_user_assignment_delete">
                                        <input type="hidden" name="nextCommand" value="get_user_details">
                                        <input type="hidden" name="assignmentId" value="${user.assignment.id}">
                                        <input type="hidden" name="userId" value="${user.id}">
                                        <button class="btn btn-secondary"><fmt:message key="button.decline" bundle="${bundle}"/></button>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr class="table-secondary">
                    <td><fmt:message key="label.user.assigned.route" bundle="${bundle}"/></td>
                    <td>${user.assignment.route.number}</td>
                </tr>
                <tr class="table-secondary">
                    <td><fmt:message key="label.user.assigned.bus" bundle="${bundle}"/></td>
                    <td>${user.assignment.bus.number}</td>
                </tr>
            </c:if>
        </table>
    </body>
</html>
