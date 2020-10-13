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
<!DOCTYPE html>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<div class="panel-body">
    <form:form method="get" action="upload" enctype="multipart/form-data" modelAttribute="files">
        <input name="id" type="hidden">${fileSaveError}
        <div class="form-group">
            <div class="col-md-offset-3 col-md-9">
                <form:button>Upload</form:button>
            </div>
        </div>
    </form:form>
</div>
<div class="panel-body">
    <table>
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
                        <input type="hidden" name="fileId" value="${userFile.id}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
                <td>
                    <a href='<c:url value="/resources/files/${userFile.name}" />' download>Download</a>
                </td>
                <%--<td>--%>
                    <%--<a href="${userFile.fileUrl}" download>Download</a>--%>
                <%--</td>--%>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
