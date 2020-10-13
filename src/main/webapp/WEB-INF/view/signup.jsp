<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Simple web userFile storage</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />"
          rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>
<div class="container">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center">Simple web userFile storage</h2>
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Register new user</div>
            </div>
            <div class="panel-body">
                <form:form action="signup" method="post" cssClass="form-horizontal" modelAttribute="user">
                    <div class="form-group">
                            <%--<label for="username" class="col-md-3 control-label">User name</label>--%>
                        <div class="col-md-9">
                            <form:input type="text" path="username" placeholder="Name"
                                        value="${user.username}" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                            <%--<label for="email" class="col-md-3 control-label">Email</label>--%>
                        <div class="col-md-9">
                            <form:input type="email" path="email" placeholder="Email" value="${user.email}"
                                        cssClass="form-control"/>${emailMessage}
                        </div>
                    </div>

                    <div class="form-group">
                            <%--<label for="password" class="col-md-3 control-label">Password</label>--%>
                        <div class="col-md-9">
                            <form:input type="password" minlength="6" path="password" placeholder="Password"
                                        cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                            <form:input type="password" minlength="6" path="passwordConfirm"
                                        placeholder="Confirm password" cssClass="form-control"/>${passwordError}
                        </div>
                    </div>
                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-md-offset-3 col-md-9">
                            <form:button cssClass="btn btn-primary">Submit</form:button>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<div class="col-md-3 control-label">--%>
                            <%--<button type="submit">Sign up</button>--%>
                                <%--${message}--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <div class="col-md-9">
                            <a href="login">Login</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>