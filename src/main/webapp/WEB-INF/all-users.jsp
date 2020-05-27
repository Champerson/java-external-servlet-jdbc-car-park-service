<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title>Users info</title>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="i18n.content" var="bundle"/>
</head>
    <body>
        <jsp:include page="logout.jsp"/>
        <jsp:include page="locale.jsp"/>
        <h1><fmt:message key="users.page.title" bundle="${bundle}"/></h1>
        <table border="3">
           <caption><fmt:message key="users.table.title" bundle="${bundle}"/></caption>
           <c:forEach items="${users}" var="user">
               <tr>
                    <td>${user.name}</td>
                    <td>${user.login}</td>
                    <td>
                        <form name="change-user-role" action="./controller" method="post">
                           <input name="command" type="hidden" value="get_user_details">
                           <input name="userId" type="hidden" value="${user.id}">
                           <button><fmt:message key="user.details" bundle="${bundle}"/></button>
                        </form>
                    </td>
                   <td>
                       <form name="delete-user" action="./controller" method="post">
                           <input name="command" type="hidden" value="delete_user">
                           <input name="userId" type="hidden" value="${user.id}">
                           <button><fmt:message key="user.delete" bundle="${bundle}"/></button>
                       </form>
                   </td>
               </tr>
           </c:forEach>
        </table>
        <br><jsp:include page="back.jsp"/>
    </body>
</html>
