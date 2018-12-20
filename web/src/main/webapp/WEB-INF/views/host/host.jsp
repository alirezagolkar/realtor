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
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyD9kOrSAog9xPLswbLnDaPB_djHHqF0EK8"></script>
    <link href="<c:url value='/static/css/mapStyle.css' />" rel="stylesheet"/>
    <script src="<c:url value="/static/js/hostMap.js"/>"></script>
    <script src="<c:url value="/static/js/lib/jquery-3.2.1.min.js"/>"></script>
</head>

<body>
<div class="generic-container">
    <%@include file="../authheader.jsp" %>
    <div class="panel panel-default">
        <form:hidden id="host" path="host" value="${hostStr}" />
        <!-- Default panel contents -->
            <div id="containerDiv">
                <table class="table table-hover" width="100%" style="height: 300px; border: none">
                    <tr>
                        <td>
                            <table style="border: none;border-collapse: collapse;">
                                <tr style="border: none;">
                                    <td style="border: none; padding: 5px"><div style="padding: 7px"><strong>${host.hostName}</strong></div></td>
                                </tr>
                                    <td style="border: none;"><div style="padding: 7px">${host.hostAddress}, ${host.hostCity}, ${host.hostState}, ${host.hostCountry}</div></td>
                                </tr>
                                <tr style="border: none;">
                                    <td style="border: none;"><div style="padding: 7px">${host.hostDetail}</div></td>
                                </tr>
                            </table>
                        </td>
                        <td width="50%">
                            <table>
                                <c:forEach items="${attachments}" var="att">
                                    <tr>
                                        <img style="border: thin; padding: 2px" width="300px" height="300px" src="/attachment/getAttachmentById?id=${att.id}"/>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>
                <table class="table table-hover" width="100%">
                    <tr>
                        <div id="hostMap-canvas"/>
                    </tr>
                </table>
            </div>
        </div>
    </div>

</body>
</html>
