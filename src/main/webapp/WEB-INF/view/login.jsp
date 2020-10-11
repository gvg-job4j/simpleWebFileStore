<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
					<div class="panel-title">User login</div>
				</div>
				<div class="panel-body">
                    <form:form action="login" cssClass="form-horizontal" method="post">
                        <div class="form-group">
                            <input type="email" name="email" placeholder="Email" align="middle"/>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" placeholder="Password" required align="middle"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Sign in" formtarget="_self"/> ${message}
                        </div>
                        <div class="form-group">
                            <a href="/signup" >Register</a>
                        </div>
                    </form:form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>