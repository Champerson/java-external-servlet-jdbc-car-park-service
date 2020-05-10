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
        <jsp:include page="locale.jsp"/>
        <h1><fmt:message key="buses.page.title" bundle="${bundle}"/></h1>
        <table border="3">
            <caption>Buses</caption>
            <c:forEach items="${buses}" var="bus">
                <tr>
                    <td>
                        <form name="edit-bus" action="./controller" method="post">
                            <input name="number" type="text" value="${bus.number}">                         <!--ADD VALIDATION-->
                            <input name="model" type="text" value="${bus.model}">                           <!--ADD VALIDATION-->
                            <input name="passengersCapacity" type="text" value="${bus.passengersCapacity}"> <!--ADD VALIDATION-->
                            <input name="mileage" type="text" value="${bus.mileage}">                       <!--ADD VALIDATION-->
                            <input name="colourEn" type="text" value="${bus.localizedColour['en_EN']}">     <!--ADD VALIDATION-->
                            <input name="colourUa" type="text" value="${bus.localizedColour['uk_UA']}">     <!--ADD VALIDATION-->
                            <input name="notesEn" type="text" value="${bus.localizedNotes['en_EN']}">       <!--ADD VALIDATION-->
                            <input name="notesUa" type="text" value="${bus.localizedNotes['uk_UA']}">       <!--ADD VALIDATION-->
                            <input name="busId" type="hidden" value="${bus.id}">
                            <input name="command" type="hidden" value="edit_bus">
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
