<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>admin page</title>
        <fmt:requestEncoding value="UTF-8" />
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="i18n.content" var="bundle"/>
    </head>
    <body>
        <jsp:include page="logout.jsp"/>
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
