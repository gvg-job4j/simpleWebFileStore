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
    <title>Sign in</title>
</head>
<body>
<div>
    <form action="signup" method="post">
        <p>
            <b>User name</b><input type="text" name="name" placeholder="Name" required/><br>
            <b>Email</b><input type="email" name="email" placeholder="Email" required/><br>
            <b>Password</b><input type="password" name="password" placeholder="Password" required/><br>
        </p>
        <p>
            <input type="submit" value="Sign up" formtarget="_self"/> ${message}
        </p>
    </form>
    <a href="index.jsp" >Return to start page</a>
</div>
</body>
</html>
