<%--
  Created by IntelliJ IDEA.
  User: GVG
  Date: 26.03.2020
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<form>
    <table>
        <tr><th> Name</th><th>email</th> </tr>
        <c:forEach var="user" items="${users}">
            <tr><td><c:out value="${user.username}"/> </td><td><c:out value="${user.email}"/></td> </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
