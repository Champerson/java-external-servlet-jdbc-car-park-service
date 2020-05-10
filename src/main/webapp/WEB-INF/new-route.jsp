<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>route creation</title>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="i18n.content" var="bundle"/>
</head>
    <body>
        <jsp:include page="logout.jsp"/>
        <h1><fmt:message key="route.add.page.title" bundle="${bundle}"/></h1>
        <form name="create-route" action="./controller" method="post">
            <p>
                <fmt:message key="route.number.label" bundle="${bundle}"/>
                <input type="text" name="number"></br>                              <!--ADD VALIDATION-->
                <fmt:message key="route.length.label" bundle="${bundle}"/>
                <input type="text" name="length"></br>                              <!--ADD VALIDATION-->
                <fmt:message key="route.description.en.label" bundle="${bundle}"/>
                <input type="edit" name="descriptionEn"></br>                       <!--ADD VALIDATION-->
                <fmt:message key="route.description.ua.label" bundle="${bundle}"/>
                <input type="text" name="descriptionUa">                            <!--ADD VALIDATION-->
            </p>
            <p>
                <input name="command" type="hidden" value="create_route">
                <input type="submit" value="<fmt:message key="submit.button" bundle="${bundle}"/>">
                <input type="reset" value="<fmt:message key="reset.button" bundle="${bundle}"/>">
            </p>
        </form>
        <br><jsp:include page="back.jsp"/>
    </body>
</html>
