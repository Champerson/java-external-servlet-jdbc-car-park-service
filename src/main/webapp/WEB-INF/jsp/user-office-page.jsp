<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.content" var="bundle"/>
<c:set var="userId" value="${user.id}" scope="request"/>
<c:set var="validationResult" value="${sessionScope.validationResult}"/>
<c:set var="validatedPassword" value="${not empty validationResult.oldPassword or not empty validationResult.oldPasswordError}"/>
<c:set var="validated" value="${not empty validationResult and not validatedPassword}"/>
<c:choose>
    <c:when test="${validated}">
        <c:set var="name" value="${validationResult.name}"/>
        <c:set var="email" value="${validationResult.email}"/>
        <c:set var="phone" value="${validationResult.phone}"/>
        <c:set var="age" value="${validationResult.age}"/>
    </c:when>
    <c:otherwise>
        <c:set var="name" value="${user.name}"/>
        <c:set var="email" value="${user.email}"/>
        <c:set var="phone" value="${user.phone}"/>
        <c:set var="age" value="${user.age}"/>
    </c:otherwise>
</c:choose>
<c:if test="${validatedPassword}">
    <c:set var="oldPassword" value="${validationResult.oldPassword}"/>
    <c:set var="newPassword" value="${validationResult.newPassword}"/>
</c:if>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

        <title><fmt:message key="title.user.office.page" bundle="${bundle}"/></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp" >
            <jsp:param name="command" value="get_user_office" />
            <jsp:param name="successMessage" value="${successMessage}" />
        </jsp:include>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="${successMessage}" bundle="${bundle}"/>
            </div>
        </c:if>

        <center><h4><fmt:message key="header.user.office" bundle="${bundle}"/></h4></center></br>
        <center><table>
            <tr style="vertical-align: top">
                <td>
                    <c:choose>
                        <c:when test="${not empty user.assignment}">
                            <table class="table table-bordered">
                                <tr>
                                    <c:choose>
                                        <c:when test="${user.assignment.acceptedByDriver eq true}">
                                            <td><fmt:message key="label.user.assignment.confirmed" bundle="${bundle}"/></td>
                                            <td>
                                                <form name="decline-driver-assignment" action="./controller" method="post">
                                                    <input type="hidden" name="command" value="edit_user_assignment_delete">
                                                    <input type="hidden" name="nextCommand" value="get_user_office">
                                                    <input type="hidden" name="assignmentId" value="${user.assignment.id}">
                                                    <input type="hidden" name="userId" value="${user.id}">
                                                    <button class="btn btn-secondary"><fmt:message key="button.decline" bundle="${bundle}"/></button>
                                                </form>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td style="vertical-align: middle"><fmt:message key="label.user.assignment.not.confirmed" bundle="${bundle}"/></td>
                                            <td>
                                                <table>
                                                    <tr>
                                                        <td style="border:none">
                                                            <form name="confirm-assignment" action="./controller" method="post">
                                                                <input name="command" type="hidden" value="edit_user_assignment_accept">
                                                                <button class="btn btn-success"><fmt:message key="button.accept" bundle="${bundle}"/></button>
                                                            </form>
                                                        </td>
                                                        <td style="border:none">
                                                            <form name="decline-driver-assignment" action="./controller" method="post">
                                                                <input type="hidden" name="command" value="edit_user_assignment_delete">
                                                                <input type="hidden" name="nextCommand" value="get_user_office">
                                                                <input type="hidden" name="assignmentId" value="${user.assignment.id}">
                                                                <input type="hidden" name="userId" value="${user.id}">
                                                                <button class="btn btn-danger"><fmt:message key="button.decline" bundle="${bundle}"/></button>
                                                            </form>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                                <tr>
                                    <td><fmt:message key="label.user.assigned.bus" bundle="${bundle}"/></td>
                                    <td>${user.assignment.bus.number}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="label.user.assigned.route" bundle="${bundle}"/></td>
                                    <td>${user.assignment.route.number}</td>
                                </tr>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <div class="card border-warning" style="width: 300px">
                                <div class="card-body text-warning">
                                    <p class="card-text"><fmt:message key="label.user.no.assignment" bundle="${bundle}"/></p>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td style="width:30px"></td>
                <td>
                    <div class="card">
                        <div class="card-body">
                            <div class="mx-auto" style="width: 300px">
                                <div class="form-group">
                                    <label for="input-login"><fmt:message key="label.user.login" bundle="${bundle}"/></label>
                                    <input id="input-login" class="form-control" type="text" disabled="true" value="${user.login}"/>
                                </div>
                                <div class="form-group">
                                    <label for="input-creationTime"><fmt:message key="label.user.creation.time" bundle="${bundle}"/></label>
                                    <fmt:parseDate value="${user.creationTime}" type="both" pattern="yyyy-MM-dd'T'HH:mm" var="userCreationTime"/>
                                    <fmt:formatDate value="${userCreationTime}" type="both" dateStyle="short" timeStyle="short" var="parsedCreationTime"/>
                                    <input id="input-creationTime" class="form-control" type="text" disabled="true" value="${parsedCreationTime}"/>
                                </div>
                            </div>
                            <form class="mx-auto" style="width: 300px" name="edit-user" action="./controller" method="post">
                                <input name="command" type="hidden" value="edit_user">
                                <input name="userId" type="hidden" value="${user.id}">
                                <div class="form-group">
                                    <label for="input-name"><fmt:message key="label.user.name" bundle="${bundle}"/></label>
                                    <carpark:input name="name" value="${name}" validated="${validated}" error="${validationResult.nameError}"/>
                                </div>
                                <div class="form-group">
                                    <label for="input-email"><fmt:message key="label.user.email" bundle="${bundle}"/><font color="red"> *</font></label>
                                    <carpark:input name="email" value="${email}" validated="${validated}" error="${validationResult.emailError}"/>
                                </div>
                                <div class="form-group">
                                    <label for="input-phone"><fmt:message key="label.user.phone" bundle="${bundle}"/><font color="red"> *</font></label>
                                    <carpark:input name="phone" value="${phone}" validated="${validated}" error="${validationResult.phoneError}"/>
                                </div>
                                <div class="form-group">
                                    <label for="input-age"><fmt:message key="label.user.age" bundle="${bundle}"/><font color="red"> *</font></label>
                                    <carpark:input name="age" value="${age}" validated="${validated}" error="${validationResult.ageError}"/>
                                </div>
                                <button class="btn btn-primary" style="width:100%"><fmt:message key="button.save" bundle="${bundle}"/></button>
                            </form>
                        </div>
                    </div>
                </td>
                <td style="width:30px"></td>
                <td>
                    <div class="card">
                        <div class="card-body">
                            <form name="edit-user-password" action="./controller" method="post">
                                <input name="command" type="hidden" value="edit_user_password">
                                <div class="form-group">
                                    <label for="input-oldPassword"><fmt:message key="label.user.password.old" bundle="${bundle}"/><font color="red"> *</font></label>
                                    <carpark:input name="oldPassword" value="${oldPassword}" validated="${validatedPassword}" error="${validationResult.oldPasswordError}"/>
                                </div>
                                <div class="form-group">
                                    <label for="input-newPassword"><fmt:message key="label.user.password.new" bundle="${bundle}"/><font color="red"> *</font></label>
                                    <carpark:input name="newPassword" value="${newPassword}" validated="${validatedPassword}" error="${validationResult.newPasswordError}"/>
                                </div>
                                <button class="btn btn-primary" style="width:100%"><fmt:message key="button.save" bundle="${bundle}"/></button>
                            </form>
                        </div>
                    </div>
                </td>
            </tr>
        </table></center>
    </body>
</html>
