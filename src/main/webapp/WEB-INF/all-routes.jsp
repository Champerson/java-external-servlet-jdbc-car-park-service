<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Routes info</title>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="i18n.content" var="bundle"/>
</head>
     <body>
        <jsp:include page="logout.jsp"/>
        <jsp:include page="locale.jsp"/>
        <h1><fmt:message key="routes.page.title" bundle="${bundle}"/></h1>
            <table border="3">
                <caption><fmt:message key="routes.table.title" bundle="${bundle}"/></caption>
                <c:forEach items="${routes}" var="route">
                    <tr>
                        <td>${route.number}</td>
                        <td>
                            <form name="open-route" action="./controller" method="post">
                                <input name="command" type="hidden" value="get_route_details">
                                <input name="routeId" type="hidden" value="${route.id}">
                                <button><fmt:message key="route.details" bundle="${bundle}"/></button>
                            </form>
                        </td>
                        <td>
                            <form name="delete-route" action="./controller" method="post">
                                <input name="command" type="hidden" value="delete_route">
                                <input name="routeId" type="hidden" value="${route.id}">
                                <button><fmt:message key="route.delete" bundle="${bundle}"/></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <form name="add-new-route" action="./controller" method="post">
                <input name="command" type="hidden" value="redirect">
                <input name="pageToRedirect" type="hidden" value="WEB-INF/new-route.jsp">
                <button><fmt:message key="route.add.new" bundle="${bundle}"/></button>
            </form><br>
            <jsp:include page="back.jsp"/>
     </body>
</html>