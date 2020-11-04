<%--
  Created by IntelliJ IDEA.
  User: GVG
  Date: 26.03.2020
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Simple web userFile storage</title>
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
                <form:form method="get" action="upload" enctype="multipart/form-data" modelAttribute="user">
                    <div class="form-group">
                        <form:input path="id" name="id" value="${user.id}" type="hidden"/>
                        <div class="col-md-9">
                            <form:button>Upload</form:button>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th> Name</th>
                        <th>Size</th>
                    </tr>
                    <c:forEach var="userFile" items="${files}">
                        <tr>
                            <td><c:out value="${userFile.name}"/></td>
                            <td><c:out value="${userFile.size}"/></td>
                            <td>
                                <form method="post" action="deleteFile">
                                    <input type="hidden" name="userId" value="${userFile.user.id}">
                                    <input type="hidden" name="fileId" value="${userFile.id}">
                                    <input type="submit" value="Delete">
                                </form>
                            </td>
                            <td>
                                    <%--<a href='<c:url value="/resources/files/${userFile.name}" />' download>Download</a>--%>
                                <%--<a href='<c:url value="${contextPath}/resources/files/${userFile.name}" />' download>Download</a>--%>
                                    <a href="${userFile.fileUrl.toString()}" target="_blank" download>Download</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
