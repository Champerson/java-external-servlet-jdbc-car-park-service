<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.content" var="bundle"/>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

        <title><fmt:message key="title.admin.menu.page" bundle="${bundle}"/></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp" >
            <jsp:param name="command" value="redirect" />
            <jsp:param name="pageToRedirect" value="WEB-INF/jsp/admin-menu-page.jsp" />
        </jsp:include>

        <center><h4><fmt:message key="header.admin.menu.page" bundle="${bundle}"/></h4></br></center>
        <div class="mx-auto" style="width: 300px">
            <form action="./controller" method="POST">
                <input name="command" type="hidden" value="get_all_users">
                <button type="submit" style="width:100%" class="btn btn-outline-primary"><fmt:message key="button.get.all.users" bundle="${bundle}"/></button>
            </form>
            <form action="./controller" method="POST">
                <input name="command" type="hidden" value="get_all_buses">
                <button type="submit" style="width:100%" class="btn btn-outline-primary mt-2"><fmt:message key="button.get.all.buses" bundle="${bundle}"/></button>
            </form>
            <form action="./controller" method="POST">
                <input name="command" type="hidden" value="get_all_routes">
                <button type="submit" style="width:100%" class="btn btn-outline-primary mt-2"><fmt:message key="button.get.all.routes" bundle="${bundle}"/></button>
            </form>
        </div>
    </body>
</html>
