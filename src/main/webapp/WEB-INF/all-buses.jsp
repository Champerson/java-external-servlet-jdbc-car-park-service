<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>buses info page</title>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="i18n.content" var="bundle"/>
</head>
    <body>
        <jsp:include page="logout.jsp"/>
        <h1><fmt:message key="buses.page.title" bundle="${bundle}"/></h1>

        <p>
            <c:if test="${not empty validationErrors.number}">
                <fmt:message key="${validationErrors.number}" bundle="${bundle}"/>
            </c:if>

            <c:if test="${not empty validationErrors.model}">
                <fmt:message key="${validationErrors.model}" bundle="${bundle}"/>
            </c:if>

            <c:if test="${not empty validationErrors.passengersCapacity}">
                <fmt:message key="${validationErrors.passengersCapacity}" bundle="${bundle}"/>
            </c:if>

            <c:if test="${not empty validationErrors.mileage}">
                <fmt:message key="${validationErrors.mileage}" bundle="${bundle}"/>
            </c:if>

            <c:if test="${not empty validationErrors.colourEn}">
                <fmt:message key="${validationErrors.colourEn}" bundle="${bundle}"/>
            </c:if>

            <c:if test="${not empty validationErrors.colourUa}">
                <fmt:message key="${validationErrors.colourUa}" bundle="${bundle}"/>
            </c:if>

            <c:if test="${not empty validationErrors.notesEn}">
                <fmt:message key="${validationErrors.notesEn}" bundle="${bundle}"/>
            </c:if>

            <c:if test="${not empty validationErrors.notesUa}">
                <fmt:message key="${validationErrors.notesUa}" bundle="${bundle}"/>
            </c:if>
        </p>

        <table border="3">
            <caption>Buses</caption>
            <c:forEach items="${buses}" var="bus">
                <tr>
                    <td>
                        <form name="edit-bus" action="./controller" method="post">
                            <fmt:message key="bus.number.label" bundle="${bundle}"/>
                            <input type="text" name="number" value="${bus.number}">

                            <fmt:message key="bus.model.label" bundle="${bundle}"/>
                            <input type="text" name="model" value="${bus.model}">

                            <fmt:message key="bus.capacity.label" bundle="${bundle}"/>
                            <input type="text" name="passengersCapacity" value="${bus.passengersCapacity}">

                            <fmt:message key="bus.mileage.label" bundle="${bundle}"/>
                            <input type="text" name="mileage" value="${bus.mileage}">

                            <fmt:message key="bus.colour.en.label" bundle="${bundle}"/>
                            <input type="text" name="colourEn" value="${bus.localizedColour['en_EN']}">

                            <fmt:message key="bus.colour.ua.label" bundle="${bundle}"/>
                            <input type="text" name="colourUa" value="${bus.localizedColour['uk_UA']}">

                            <fmt:message key="bus.notes.en.label" bundle="${bundle}"/>
                            <input type="text" name="notesEn" value="${bus.localizedNotes['en_EN']}">

                            <fmt:message key="bus.notes.ua.label" bundle="${bundle}"/>
                            <input type="text" name="notesUa" value="${bus.localizedNotes['uk_UA']}">

                            <input type="hidden" name="busId" value="${bus.id}">
                            <input type="hidden" name="command" value="edit_bus">
                            <button><fmt:message key="save.button" bundle="${bundle}"/></button>
                        </form>
                    </td>
                    <td>
                        <form name="delete-bus" action="./controller" method="post">
                            <input name="busId" type="hidden" value="${bus.id}">
                            <input name="command" type="hidden" value="delete_bus">
                            <button><fmt:message key="bus.delete" bundle="${bundle}"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form name="add-new-bus" action="./controller" method="post">
            <input name="pageToRedirect" type="hidden" value="WEB-INF/new-bus.jsp">
            <input name="command" type="hidden" value="redirect">
            <button><fmt:message key="bus.add.new" bundle="${bundle}"/></button>
        </form>
        <br><jsp:include page="back.jsp"/>
    </body>
</html>
