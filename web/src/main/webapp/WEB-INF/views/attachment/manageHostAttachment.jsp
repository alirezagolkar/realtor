<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 6/6/2017
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <title>realtor</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>

<body>
<div class="generic-container">
    <%@include file="../authheader.jsp" %>
    ${success}

    <form:form method="POST" enctype="multipart/form-data" modelAttribute="attachment" class="form-horizontal">
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="fileContent">Choose a file</label>
                <div class="col-md-7">
                    <form:input type="file" path="fileContent" id="fileContent" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="fileContent" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-actions floatRight">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/host/hostList' />">Cancel</a>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Submit" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/host/hostList' />">Cancel</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>

    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Your rentals</span></div>
        <form:form method="POST" modelAttribute="attachment" class="form-horizontal">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Rental Title</th>
                        <th>Creation date</th>
                        <th>Rental files (Images, PDF, TEXT File, etc</th>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                            <th width="100"></th>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <th width="100"></th>
                        </sec:authorize>

                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${attachments}" var="attachment">
                    <tr>
                        <td>${attachment.host.hostName}</td>
                        <td>${attachment.creationDate}</td>
                        <td><img width="150px" height="100px" src="/attachment/getAttachmentById?id=${attachment.id}"/></td>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                            <td><a href="<c:url value='/host/edit-host-file-${attachment.id}' />" class="btn btn-success custom-width">edit</a></td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <td><a href="<c:url value='/attachment/delete-attachment-${attachment.id}' />" class="btn btn-danger custom-width">delete</a></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form:form>
    </div>


</div>
</body>
</html>
