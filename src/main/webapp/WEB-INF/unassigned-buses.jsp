<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="i18n.content" var="bundle"/>
    <title>unassigned buses</title>
</head>
    <body>
        <jsp:include page="logout.jsp"/>
        <jsp:include page="locale.jsp"/>
        <h1><fmt:message key="unassigned.buses.page.title" bundle="${bundle}"/></h1>
        <table border="3">
            <c:forEach items="${buses}" var="bus">
                <tr>
                    <td>${bus.number}</td>
                    <td>
                        <form name="assign-bus" action="./controller" method="post">
                            <input type="hidden" name="routeId" value="${routeId}">
                            <input type="hidden" name="busId" value="${bus.id}">
                            <input type="hidden" name="command" value="create_bus_assignment">
                            <button><fmt:message key="assign.button" bundle="${bundle}"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br><jsp:include page="back.jsp"/>
    </body>
</html>
