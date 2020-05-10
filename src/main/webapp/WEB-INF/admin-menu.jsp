<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin page</title>
        <fmt:requestEncoding value="UTF-8" />
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="i18n.content" var="bundle"/>
    </head>
    <body>
        <jsp:include page="logout.jsp"/>
        <jsp:include page="locale.jsp"/>
        <h2><fmt:message key="admin.page.title" bundle="${bundle}"/></h2>
        <form action="./controller" method="POST">
            <input name="command" type="hidden" value="get_all_users">
            <br><button><fmt:message key="get.all.users.button" bundle="${bundle}"/></button>
        </form>
        <form action="./controller" method="POST">
            <input name="command" type="hidden" value="get_all_buses">
            <br><button><fmt:message key="get.all.buses.button" bundle="${bundle}"/></button>
        </form>
        <form action="./controller" method="POST">
            <input name="command" type="hidden" value="get_all_routes">
            <br><button><fmt:message key="get.all.routes.button" bundle="${bundle}"/></button>
        </form>
    </body>
</html>
