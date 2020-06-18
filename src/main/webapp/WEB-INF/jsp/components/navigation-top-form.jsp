<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.content" var="bundle"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <ul class="navbar-nav">
        <li class="nav-item">
            <form name="localeEn" action="./controller" method="post">
                <input name="command" type="hidden" value="${param.command}">
                <input name="locale" type="hidden" value="en_EN">
                <c:if test="${not empty param.pageToRedirect}">
                    <input name="pageToRedirect" type="hidden" value="${param.pageToRedirect}">
                </c:if>
                <c:if test="${not empty param.successMessage}">
                    <input name="successMessage" type="hidden" value="${param.successMessage}">
                </c:if>
                <c:if test="${not empty param.errorMessage}">
                    <input name="errorMessage" type="hidden" value="${param.errorMessage}">
                </c:if>
                <c:if test="${not empty userId}">
                    <input name="userId" type="hidden" value="${userId}">
                </c:if>
                <c:if test="${not empty busId}">
                    <input name="busId" type="hidden" value="${busId}">
                </c:if>
                <c:if test="${not empty routeId}">
                    <input name="routeId" type="hidden" value="${routeId}">
                </c:if>
                <c:if test="${not empty assignmentId}">
                    <input name="assignmentId" type="hidden" value="${assignmentId}">
                </c:if>
                <button class="btn btn-outline-primary"><fmt:message key="button.locale.en" bundle="${bundle}"/></button>
            </form>
        </li>
        <li class="nav-item ml-2">
            <form name="localeUa" action="./controller" method="post">
                <input name="command" type="hidden" value="${param.command}">
                <input name="locale" type="hidden" value="uk_UA">
                <c:if test="${not empty param.pageToRedirect}">
                    <input name="pageToRedirect" type="hidden" value="${param.pageToRedirect}">
                </c:if>
                <c:if test="${not empty param.successMessage}">
                    <input name="successMessage" type="hidden" value="${param.successMessage}">
                </c:if>
                <c:if test="${not empty param.errorMessage}">
                    <input name="errorMessage" type="hidden" value="${param.errorMessage}">
                </c:if>
                <c:if test="${not empty userId}">
                    <input name="userId" type="hidden" value="${userId}">
                </c:if>
                <c:if test="${not empty busId}">
                    <input name="busId" type="hidden" value="${busId}">
                </c:if>
                <c:if test="${not empty routeId}">
                    <input name="routeId" type="hidden" value="${routeId}">
                </c:if>
                <c:if test="${not empty assignmentId}">
                    <input name="assignmentId" type="hidden" value="${assignmentId}">
                </c:if>
                <button class="btn btn-outline-primary"><fmt:message key="button.locale.ua" bundle="${bundle}"/></button>
            </form>
        </li>
        <c:if test="${not empty sessionScope.userId}">
            <li class="nav-item ml-2">
                <form name="logout" action="./controller" method="post">
                    <input name="command" type="hidden" value="logout">
                    <button class="btn btn-outline-secondary bg-light"><fmt:message key="button.logout" bundle="${bundle}"/></button>
                </form>
            </li>
        </c:if>
        <c:if test="${not empty param.backButton}">
            <li class="nav-item ml-2">
                <form name="back" action="./controller" method="post">
                    <input name="command" type="hidden" value="redirect">
                    <input name="pageToRedirect" type="hidden" value="WEB-INF/jsp/admin-menu-page.jsp">
                    <button class="btn btn-outline-secondary bg-light"><fmt:message key="button.back" bundle="${bundle}"/></button>
                </form>
            </li>
        </c:if>
    </ul>
</nav>
</br>
