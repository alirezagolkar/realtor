<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.artinrayan.foodi.web.util.UserUtil" %>
	<div class="authbar">
		<span>Dear <strong><%=UserUtil.getCurrentUser().getFullName()%></strong>, Welcome to Realtor</span>
			<span class="floatRight">
				<a class="menu-bar" href="<c:url value="/host/" />">Home</a>
				<a class="menu-bar" href="<c:url value="/host/hostList" />">Manage Rentals</a>
				<sec:authorize access="hasRole('ADMIN')">
					<a class="menu-bar" href="<c:url value="/user/userList" />">Manage Users</a>
				</sec:authorize>
				<a class="menu-bar" href="<c:url value="/logout" />">Logout</a>
			</span>
	</div>
