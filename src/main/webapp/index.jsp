<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <fmt:requestEncoding value="UTF-8" />
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="i18n.content" var="bundle"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>Log in</title>
        </head>
    <body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <jsp:include page="WEB-INF/index-register-locale.jsp"/>
        <h1><fmt:message key="main.page.title" bundle="${bundle}"/></h1>
        <form name="authorization" action="./controller" method="post">
            <p>
                <b><fmt:message key="login.label" bundle="${bundle}"/></b><br> <!--ADD VALIDATION-->
                <input type="text" name="login">
            </p>
            <p>
                <b><fmt:message key="password.label" bundle="${bundle}"/></b><br> <!--ADD VALIDATION-->
                <input type="password" name="password">
            </p>
            <p>
                <input type="submit" value="<fmt:message key="submit.log.in.button" bundle="${bundle}"/>">
                <input type="reset" value="<fmt:message key="reset.button" bundle="${bundle}"/>">
            </p>
            <input name="command" type="hidden" value="authorization">
        </form>
        <a href="registration.jsp"><fmt:message key="registration.link" bundle="${bundle}"/></a>
    </body>
</html>