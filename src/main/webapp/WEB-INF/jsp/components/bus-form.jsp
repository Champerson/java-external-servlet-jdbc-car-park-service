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
        <c:set var="model" value="${validationResult.model}"/>
        <c:set var="passengersCapacity" value="${validationResult.passengersCapacity}"/>
        <c:set var="mileage" value="${validationResult.mileage}"/>
        <c:set var="colourEn" value="${validationResult.colourEn}"/>
        <c:set var="colourUa" value="${validationResult.colourUa}"/>
        <c:set var="notesEn" value="${validationResult.notesEn}"/>
        <c:set var="notesUa" value="${validationResult.notesUa}"/>
    </c:when>
    <c:otherwise>
        <c:set var="number" value="${bus.number}"/>
        <c:set var="model" value="${bus.model}"/>
        <c:set var="passengersCapacity" value="${bus.passengersCapacity}"/>
        <c:set var="mileage" value="${bus.mileage}"/>
        <c:set var="colourEn" value="${bus.localizedColour['en_EN']}"/>
        <c:set var="colourUa" value="${bus.localizedColour['uk_UA']}"/>
        <c:set var="notesEn" value="${bus.localizedNotes['en_EN']}"/>
        <c:set var="notesUa" value="${bus.localizedNotes['uk_UA']}"/>
    </c:otherwise>
</c:choose>

<div class="mx-auto" style="width: 300px">
    <form name="bus" action="./controller" method="post">
        <input name="command" type="hidden" value="${param.command}">
        <input name="busId" type="hidden" value="${bus.id}">
        <div class="form-group">
            <label for="input-number"><fmt:message key="label.bus.number" bundle="${bundle}"/><font color="red"> *</font></label>
            <carpark:input name="number" value="${number}" validated="${validated}" error="${validationResult.numberError}"/>
        </div>
        <div class="form-group">
            <label for="input-model"><fmt:message key="label.bus.model" bundle="${bundle}"/></label>
            <carpark:input name="model" value="${model}" validated="${validated}" error="${validationResult.modelError}"/>
        </div>
        <div class="form-group">
            <label for="input-passengersCapacity"><fmt:message key="label.bus.capacity" bundle="${bundle}"/><font color="red"> *</font></label>
            <carpark:input name="passengersCapacity" value="${passengersCapacity}" validated="${validated}" error="${validationResult.passengersCapacityError}"/>
        </div>
        <div class="form-group">
            <label for="input-mileage"><fmt:message key="label.bus.mileage" bundle="${bundle}"/><font color="red"> *</font></label>
            <carpark:input name="mileage" value="${mileage}" validated="${validated}" error="${validationResult.mileageError}"/>
        </div>
        <div class="form-group">
            <label for="input-colourEn"><fmt:message key="label.bus.colour.en" bundle="${bundle}"/></label>
            <carpark:input name="colourEn" value="${colourEn}" validated="${validated}" error="${validationResult.colourEnError}"/>
        </div>
        <div class="form-group">
            <label for="input-colourUa"><fmt:message key="label.bus.colour.ua" bundle="${bundle}"/></label>
            <carpark:input name="colourUa" value="${colourUa}" validated="${validated}" error="${validationResult.colourUaError}"/>
        </div>
        <div class="form-group">
            <label for="input-notesEn"><fmt:message key="label.bus.notes.en" bundle="${bundle}"/></label>
            <carpark:input name="notesEn" value="${notesEn}" validated="${validated}" error="${validationResult.notesEnError}"/>
        </div>
        <div class="form-group">
            <label for="input-notesUa"><fmt:message key="label.bus.notes.ua" bundle="${bundle}"/></label>
            <carpark:input name="notesUa" value="${notesUa}" validated="${validated}" error="${validationResult.notesUaError}"/>
        </div>
        <button class="btn btn-primary" type="submit" style="width:100%"><fmt:message key="button.save" bundle="${bundle}"/></button>
    </form>
</div>