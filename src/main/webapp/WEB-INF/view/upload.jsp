<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="panel-title">User files</div>
            </div>
            <div class="panel-body">
                <form:form method="post" action="uploadFile" enctype="multipart/form-data" modelAttribute="user">
                    <div class="form-group">
                        <form:input path="id" name="id" value="${user.id}" type="hidden"/>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped table-bordered">
                            <tr>
                                <td>Select a file to upload</td>
                                <td><input type="file" name="file"></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Upload"></td>
                                <td></td>
                            </tr>
                        </table>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>