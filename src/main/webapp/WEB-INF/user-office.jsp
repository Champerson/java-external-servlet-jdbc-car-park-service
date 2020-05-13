<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>car park service driver office</title>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="i18n.content" var="bundle"/>
</head>
    <body>
        <jsp:include page="logout.jsp"/>
        <h1><fmt:message key="user.office.title" bundle="${bundle}"/> ${user.login}</h1>

        <form name="edit-user" action="./controller" method="post">
            <p>
                <b><fmt:message key="name.label" bundle="${bundle}"/></b><br>
                <c:if test="${not empty validationErrors.name}">
                    <fmt:message key="${validationErrors.name}" bundle="${bundle}"/><br>
                </c:if>
                <input type="text" name="name" value="${user.name}">
            </p>
            <p>
                <b><fmt:message key="email.label" bundle="${bundle}"/></b><br>
                <c:if test="${not empty validationErrors.email}">
                    <fmt:message key="${validationErrors.email}" bundle="${bundle}"/><br>
                </c:if>
                <input type="text" name="email" value="${user.email}">
            </p>
            <p>
                <b><fmt:message key="phone.label" bundle="${bundle}"/></b><br>
                <c:if test="${not empty validationErrors.phone}">
                    <fmt:message key="${validationErrors.phone}" bundle="${bundle}"/><br>
                </c:if>
                <input type="text" name="phone" value="${user.phone}">
            </p>
            <p>
                <b><fmt:message key="age.label" bundle="${bundle}"/></b><br>
                <c:if test="${not empty validationErrors.age}">
                    <fmt:message key="${validationErrors.age}" bundle="${bundle}"/><br>
                </c:if>
                <input type="text" name="age" value="${user.age}">
            </p>
            <p>
                <input type="submit" value="<fmt:message key="save.button" bundle="${bundle}"/>">
            </p>
            <input name="command" type="hidden" value="edit_user">
        </form>
        <form name="edit-user-password" action="./controller" method="post">
            <p><b><fmt:message key="change.password.title" bundle="${bundle}"/></b></p>
            <p>
                <b><fmt:message key="old.password.label" bundle="${bundle}"/></b><br>
                <c:if test="${not empty validationErrors.password}">
                    <fmt:message key="${validationErrors.password}" bundle="${bundle}"/><br>
                </c:if>
                <input type="text" name="oldPassword">
            </p>
            <p>
                <b><fmt:message key="new.password.label" bundle="${bundle}"/></b><br>
                <c:if test="${not empty validationErrors.password}">
                    <fmt:message key="${validationErrors.password}" bundle="${bundle}"/><br>
                </c:if>
                <input type="text" name="newPassword">
            </p>
            <p>
                <input type="submit" value="<fmt:message key="save.button" bundle="${bundle}"/>">
                <input type="reset" value="<fmt:message key="reset.button" bundle="${bundle}"/>">
            </p>
            <input name="command" type="hidden" value="edit_user_password">
        </form>
        <c:if test="${not empty user.assignment}">
            <c:if test = "${user.assignment.acceptedByDriver == true}">
                <p><fmt:message key="user.assignment.title" bundle="${bundle}"/></p>
            </c:if>
            <c:if test="${user.assignment.acceptedByDriver == false}">
                <form name="confirm-assignment" action="./controller" method="post">
                    <input name="command" type="hidden" value="edit_user_assignment_accept">
                    <p><input type="submit" value="<fmt:message key="user.assignment.accept.button" bundle="${bundle}"/>"></p>
                </form>
            </c:if>
                <table border="3">
                    <caption><fmt:message key="user.assigned.bus.caption.title" bundle="${bundle}"/></caption>
                    <tr>
                        <th><fmt:message key="bus.number.title" bundle="${bundle}"/></th>
                        <th><fmt:message key="model.title" bundle="${bundle}"/></th>
                        <th><fmt:message key="colour.title" bundle="${bundle}"/></th>
                    </tr>
                    <tr>
                        <td>${user.assignment.bus.number}</td>
                        <td>${user.assignment.bus.model}</td>
                        <td>${user.assignment.bus.localizedColour[locale]}</td>
                    </tr>
                </table>
                <table border="3">
                    <caption><fmt:message key="user.assigned.route.caption.title" bundle="${bundle}"/></caption>
                    <tr>
                        <th><fmt:message key="bus.number.title" bundle="${bundle}"/></th>
                        <th><fmt:message key="length.title" bundle="${bundle}"/></th>
                        <th><fmt:message key="description.title" bundle="${bundle}"/></th>
                    </tr>
                    <tr>
                        <td>${user.assignment.route.number}</td>
                        <td>${user.assignment.route.length}</td>
                        <td>${user.assignment.route.localizedDescription[locale]}</td>
                    </tr>
                </table>
        </c:if>
    </body>
</html>
