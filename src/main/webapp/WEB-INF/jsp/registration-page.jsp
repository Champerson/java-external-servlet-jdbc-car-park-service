<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.content" var="bundle"/>
<c:set var="validationResult" value="${sessionScope.validationResult}"/>
<c:set var="validated" value="${not empty validationResult}"/>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

        <title><fmt:message key="title.registration.page" bundle="${bundle}"/></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp" >
            <jsp:param name="command" value="redirect" />
            <jsp:param name="pageToRedirect" value="WEB-INF/jsp/registration-page.jsp" />
        </jsp:include>

        <center><h4><fmt:message key="header.registration.page" bundle="${bundle}"/></h4></center></br>
        <div class="mx-auto" style="width: 300px">
            <form name="registration" action="./controller" method="post">
                <input name="command" type="hidden" value="create_user">
                <div class="form-group">
                    <label for="input-login"><fmt:message key="label.user.login" bundle="${bundle}"/><font color="red"> *</font></label>
                    <carpark:input name="login" value="${validationResult.login}" validated="${validated}" error="${validationResult.loginError}" placeholder="John"/>
                </div>
                <div class="form-group">
                    <label for="input-password"><fmt:message key="label.user.password" bundle="${bundle}"/><font color="red"> *</font></label>
                    <carpark:input name="password" value="${validationResult.password}" validated="${validated}" error="${validationResult.passwordError}" placeholder="John123"/>
                </div>
                <div class="form-group">
                    <label for="input-name"><fmt:message key="label.user.name" bundle="${bundle}"/></label>
                    <carpark:input name="name" value="${validationResult.name}" validated="${validated}" error="${validationResult.nameError}" placeholder="John Smith"/>
                </div>
                <div class="form-group">
                    <label for="input-email"><fmt:message key="label.user.email" bundle="${bundle}"/><font color="red"> *</font></label>
                    <carpark:input name="email" value="${validationResult.email}" validated="${validated}" error="${validationResult.emailError}" placeholder="john.smith@gmail.com"/>
                </div>
                <div class="form-group">
                    <label for="input-phone"><fmt:message key="label.user.phone" bundle="${bundle}"/><font color="red"> *</font></label>
                    <carpark:input name="phone" value="${validationResult.phone}" validated="${validated}" error="${validationResult.phoneError}" placeholder="0672234590"/>
                </div>
                <div class="form-group">
                    <label for="input-age"><fmt:message key="label.user.age" bundle="${bundle}"/><font color="red"> *</font></label>
                    <carpark:input name="age" value="${validationResult.age}" validated="${validated}" error="${validationResult.ageError}" placeholder="35"/>
                </div>
                <button class="btn btn-primary" type="submit"><fmt:message key="button.register" bundle="${bundle}"/></button>
            </form>
        </div>
    </body>
</html>