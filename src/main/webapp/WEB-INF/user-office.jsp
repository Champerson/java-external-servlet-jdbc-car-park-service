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
        <jsp:include page="locale.jsp"/>
        <h1><fmt:message key="user.office.title" bundle="${bundle}"/> ${user.login}</h1>

        <form name="edit-user" action="./controller" method="post">
            <p>
                <b><fmt:message key="name.label" bundle="${bundle}"/></b><br>
                <input type="text" name="name" value="${user.name}">            <!--ADD VALIDATION-->
            </p>
            <p>
                <b><fmt:message key="email.label" bundle="${bundle}"/></b><br>
                <input type="text" name="email" value="${user.email}">          <!--ADD VALIDATION-->
            </p>
            <p>
                <b><fmt:message key="phone.label" bundle="${bundle}"/></b><br>
                <input type="text" name="phone" value="${user.phone}">          <!--ADD VALIDATION-->
            </p>
            <p>
                <b><fmt:message key="age.label" bundle="${bundle}"/></b><br>
                <input type="text" name="age" value="${user.age}">              <!--ADD VALIDATION-->
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
                <input type="text" name="oldPassword">
            </p>
            <p>
                <b><fmt:message key="new.password.label" bundle="${bundle}"/></b><br>
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
                <table>
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
