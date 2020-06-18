<%@ page contentType="text/html" pageEncoding="UTF-8" %>
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

        <title><fmt:message key="title.index.page" bundle="${bundle}"/></title>
    </head>
    <body>
        <jsp:include page="WEB-INF/jsp/components/navigation-top-form.jsp" >
            <jsp:param name="command" value="redirect" />
            <jsp:param name="pageToRedirect" value="index.jsp" />
            <jsp:param name="successMessage" value="${successMessage}" />
            <jsp:param name="errorMessage" value="${errorMessage}" />
        </jsp:include>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="${successMessage}" bundle="${bundle}"/>
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="${errorMessage}" bundle="${bundle}"/>
            </div>
        </c:if>

        <center><h4><fmt:message key="header.index.page" bundle="${bundle}"/></h4></center></br>
        <div class="mx-auto" style="width: 300px">
            <form name="authorization" action="./controller" method="post">
                <input name="command" type="hidden" value="authorization">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputLoginLabel"><fmt:message key="label.user.login" bundle="${bundle}"/></span>
                    </div>
                    <input type="edit" class="form-control" id="inputLogin" aria-describedby="inputLoginLabel" name="login">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputPasswordLabel"><fmt:message key="label.user.password" bundle="${bundle}"/></span>
                    </div>
                    <input type="password" class="form-control" id="inputPassword" aria-describedby="inputPasswordLabel" name="password">
                </div>
                <button style="width:100%" class="btn btn-primary"><fmt:message key="button.authorization" bundle="${bundle}"/></button>
            </form></br>
            <form name="openRegistration" action="./controller" method="post">
                <input name="command" type="hidden" value="redirect">
                <input name="pageToRedirect" type="hidden" value="WEB-INF/jsp/registration-page.jsp">
                <button style="width:100%" class="btn btn-outline-primary"><fmt:message key="button.open.registration" bundle="${bundle}"/></button>
            </form>
        </div>
    </body>
</html>