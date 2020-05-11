<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>driver office</title>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="i18n.content" var="bundle"/>
</head>
    <body>
    <jsp:include page="logout.jsp"/>
    <h1>${user.name} <fmt:message key="user.details.page.title" bundle="${bundle}"/></h1>
        <table border="3">
            <tr>
                <td>${user.name}</td>
            </tr>
            <tr>
                <td>${user.login}</td>
            </tr>
            <tr>
                <td>
                    <form name="save-user-role" action="./controller" method="post">
                        <input type="hidden" name="command" value="edit_user_role">
                        <input type="hidden" name="userId" value="${user.id}">
                        <p><fmt:message key="user.role.title" bundle="${bundle}"/> ${user.accessRole}</p>
                        <select name="accessRole" size="1">
                            <option value="DRIVER"><fmt:message key="user.role.driver.select" bundle="${bundle}"/></option>
                            <option value="ADMIN"><fmt:message key="user.role.admin.select" bundle="${bundle}"/></option>
                        </select>
                        <p><input type="submit" value="<fmt:message key="save.button" bundle="${bundle}"/>"></p>
                    </form>
                </td>
            </tr>
        </table>
            <c:if test="${not empty assignment}">
                <br>
                <table border="3">
                    <caption><fmt:message key="assignment.caption.title" bundle="${bundle}"/></caption>
                    <tr>
                        <th><fmt:message key="bus.number.title" bundle="${bundle}"/></th>
                        <th><fmt:message key="route.number.title" bundle="${bundle}"/></th>
                    </tr>
                    <tr>
                        <td>${assignment.bus.number}</td>
                        <td>${assignment.route.number}</td>
                    </tr>
                </table>
                <form name="decline-assignment" action="./controller" method="post">
                    <input type="hidden" name="command" value="edit_user_assignment_delete">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button><b><fmt:message key="user.decline.assignment" bundle="${bundle}"/></b></button>
                </form>
            </c:if>
    <br><jsp:include page="back.jsp"/>
    </body>
</html>
