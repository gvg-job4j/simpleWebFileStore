<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Simple web file storage</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />"
          rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center">Simple web file storage</h2>
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Login</div>
            </div>
            <div class="panel-body">
                <form:form action="login" cssClass="form-horizontal" method="post" modelAttribute="user">
                    <div class="form-group">
                        <div class="col-md-12">
                            <form:input type="email" name="email" path="email" placeholder="Email"
                                        cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12">
                            <form:input type="password" name="password" path="password" placeholder="Password"
                                        cssClass="form-control" required="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-md-offset-4 col-md-12">
                            <form:button cssClass="btn btn-primary">Sign in</form:button> or <a
                                href="signup">Register</a> ${message}
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>