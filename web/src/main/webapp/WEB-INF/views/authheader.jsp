<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.artinrayan.foodi.web.util.UserUtil" %>
	<div class="authbar">
		<span>Dear <strong><%=UserUtil.getCurrentUser().getFullName()%></strong>, Welcome to Realtor</span>
			<span class="floatRight">
				<a class="menu-bar" href="<c:url value="/home" />">Home</a>
				<a class="menu-bar" href="<c:url value="/host/unitList" />">Manage Units</a>
				<sec:authorize access="hasRole('ADMIN')">
					<a class="menu-bar" href="<c:url value="/user/userList" />">Manage Users</a>
				</sec:authorize>
				<a class="menu-bar" href="<c:url value="/logout" />">Logout</a>
				<%--<a class="menu-bar" href="<c:url value="/testUser" />">testUser</a>--%>
				<%--<a class="menu-bar" href="<c:url value="/testHost" />">testHost</a>--%>
				<%--<a href="<c:url value="/user/angularMap" />">angularMap</a>--%>
			</span>
	</div>
