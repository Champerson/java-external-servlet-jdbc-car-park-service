<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title>unassigned drivers</title>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="i18n.content" var="bundle"/>
</head>
    <body>
        <jsp:include page="logout.jsp"/>
        <jsp:include page="locale.jsp"/>
        <h1><fmt:message key="unassigned.drivers.page.title" bundle="${bundle}"/></h1>
        <table border="3">
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.login}</td>
                    <td>${user.phone}</td>
                    <td>${user.email}</td>
                    <td>
                        <form name="assign-driver" action="./controller" method="post">
                            <input name="routeId" type="hidden" value="${routeId}">
                            <input name="assignmentId" type="hidden" value="${assignmentId}">
                            <input name="userId" type="hidden" value="${user.id}">
                            <input name="command" type="hidden" value="create_driver_assignment">
                            <button><fmt:message key="assign.button" bundle="${bundle}"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br><jsp:include page="back.jsp"/>
    </body>
</html>
