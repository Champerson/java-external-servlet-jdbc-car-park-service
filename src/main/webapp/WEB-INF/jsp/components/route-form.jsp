<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.content" var="bundle"/>
<c:set var="validationResult" value="${sessionScope.validationResult}"/>
<c:set var="validated" value="${not empty validationResult}"/>
<c:choose>
    <c:when test="${validated}">
        <c:set var="number" value="${validationResult.number}"/>
        <c:set var="length" value="${validationResult.length}"/>
        <c:set var="descriptionEn" value="${validationResult.descriptionEn}"/>
        <c:set var="descriptionUa" value="${validationResult.descriptionUa}"/>
    </c:when>
    <c:otherwise>
        <c:set var="number" value="${route.number}"/>
        <c:set var="length" value="${route.length}"/>
        <c:set var="descriptionEn" value="${route.localizedDescription['en_EN']}"/>
        <c:set var="descriptionUa" value="${route.localizedDescription['uk_UA']}"/>
    </c:otherwise>
</c:choose>

<div class="mx-auto" style="width: 300px">
    <form name="route" action="./controller" method="post">
        <input name="command" type="hidden" value="${param.command}">
        <input name="routeId" type="hidden" value="${route.id}">
        <div class="form-group">
            <label for="input-number"><fmt:message key="label.route.number" bundle="${bundle}"/><font color="red"> *</font></label>
            <carpark:input name="number" value="${number}" validated="${validated}" error="${validationResult.numberError}"/>
        </div>
        <div class="form-group">
            <label for="input-length"><fmt:message key="label.route.length" bundle="${bundle}"/><font color="red"> *</font></label>
            <carpark:input name="length" value="${length}" validated="${validated}" error="${validationResult.lengthError}"/>
        </div>
        <div class="form-group">
            <label for="input-descriptionEn"><fmt:message key="label.route.description.en" bundle="${bundle}"/><font color="red"> *</font></label>
            <carpark:input name="descriptionEn" value="${descriptionEn}" validated="${validated}" error="${validationResult.descriptionEnError}"/>
        </div>
        <div class="form-group">
            <label for="input-descriptionUa"><fmt:message key="label.route.description.ua" bundle="${bundle}"/><font color="red"> *</font></label>
            <carpark:input name="descriptionUa" value="${descriptionUa}" validated="${validated}" error="${validationResult.descriptionUaError}"/>
        </div>
        <button class="btn btn-primary" type="submit" style="width:100%"><fmt:message key="button.save" bundle="${bundle}"/></button>
    </form>
</div>