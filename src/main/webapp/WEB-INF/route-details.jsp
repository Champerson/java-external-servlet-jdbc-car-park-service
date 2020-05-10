<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>route details</title>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="i18n.content" var="bundle"/>
</head>
    <body>
        <jsp:include page="logout.jsp"/>
        <h1><fmt:message key="route.page.title" bundle="${bundle}"/> ${route.number}</h1>
        <form name="edit-route-name" action="./controller" method="post">
            <p>
                <fmt:message key="route.number.label" bundle="${bundle}"/>
                <input type="text" name="number" value="${route.number}"></br>                                <!--ADD VALIDATION-->
                <fmt:message key="route.length.label" bundle="${bundle}"/>
                <input type="text" name="length" value="${route.length}"></br>                                <!--ADD VALIDATION-->
                <fmt:message key="route.description.en.label" bundle="${bundle}"/>
                <input type="text" name="descriptionEn" value="${route.localizedDescription['en_EN']}"></br>  <!--ADD VALIDATION-->
                <fmt:message key="route.description.ua.label" bundle="${bundle}"/>
                <input type="text" name="descriptionUa" value="${route.localizedDescription['uk_UA']}">       <!--ADD VALIDATION-->
                <input type="hidden" name="routeId" value="${route.id}">
                <input type="hidden" name="command" value="edit_route">
            </p>
            <p>
                <input type="submit" value="<fmt:message key="save.button" bundle="${bundle}"/>">
                <input type="reset" value="<fmt:message key="reset.button" bundle="${bundle}"/>">
            </p>
        </form>
        <table border="3">
            <caption><b><fmt:message key="route.buses.table.title" bundle="${bundle}"/></b></caption>
            <c:if test="${not empty route.assignments}">
                <c:forEach items="${route.assignments}" var="assignment">
                    <tr>
                        <td>${assignment.bus.number}</td>
                        <c:if test="${empty assignment.driver}">
                            <td>
                                <form name="assign-driver" action="./controller" method="post">
                                    <input type="hidden" name="command" value="get_unassigned_drivers">
                                    <input type="hidden" name="busId" value="${assignment.bus.id}">
                                    <input type="hidden" name="routeId" value="${route.id}">
                                    <input type="hidden" name="assignmentId" value="${assignment.id}">
                                    <button><fmt:message key="route.add.driver.button" bundle="${bundle}"/></button>
                                </form>
                            </td>
                        </c:if>
                        <c:if test="${not empty assignment.driver}">
                            <td>
                                <c:if test="${assignment.acceptedByDriver eq true}">
                                    <fmt:message key="route.confirmed" bundle="${bundle}"/>
                                </c:if>
                                <c:if test="${assignment.acceptedByDriver eq false}">
                                    <fmt:message key="route.not.confirmed" bundle="${bundle}"/>
                                </c:if>
                            </td>
                        </c:if>
                        <td>
                            <form name="delete-assignment" action="./controller" method="post">
                                <input type="hidden" name="command" value="delete_assignment">
                                <input type="hidden" name="assignmentId" value="${assignment.id}">
                                <input type="hidden" name="routeId" value="${route.id}">
                                <button><fmt:message key="route.decline.button" bundle="${bundle}"/></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table><br>
        <form name="add-bus" action="./controller" method="post">
            <input name="command" type="hidden" value="get_unassigned_buses">
            <input name="routeId" type="hidden" value="${route.id}">
            <button><fmt:message key="route.add.bus.button" bundle="${bundle}"/></button>
        </form>
        <jsp:include page="back.jsp"/>
    </body>
</html>
