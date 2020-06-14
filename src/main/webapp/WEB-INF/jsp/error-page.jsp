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

        <title><fmt:message key="title.error.page" bundle="${bundle}"/></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp" >
            <jsp:param name="command" value="redirect" />
            <jsp:param name="pageToRedirect" value="WEB-INF/jsp/error-page.jsp" />
            <jsp:param name="additionalParameterName" value="errorMessage" />
            <jsp:param name="additionalParameterValue" value="${errorMessage}" />
        </jsp:include>

        <div class="card mx-auto" style="width: 18rem">
            <div class="card-header text-white bg-danger mb-3">
                <fmt:message key="header.error.page" bundle="${bundle}"/>
            </div>
            <div class="card-body">
              <p class="card-text"><fmt:message key="${errorMessage}" bundle="${bundle}"/></p>
            </div>
        </div>
    </body>
</html>
