<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="iLearnCSS/iLearnCSS.css">
</head>
<body class="topBar">
	<!-- Refresh On Reload Script -->
	<jsp:include page="refreshOnReloadScript.jsp" />

	<!-- Start Imports -->
	<%@ page import="edu.ben.cmsc398.iLearn.Model.*"%>
	<!-- End Imports -->

	<!-- Start User Verification -->
	<%
		// Initialize A User Object To Null
		User user = null;

		// Initialize A Boolean Variable To False
		boolean verified = false;

		// If A Session Exists and A User Object Is In The Session,
		// Change The Varified Boolean Value To True, And Change The User Object
		// To The One Retrieved From The Session
		if (session != null && session.getAttribute("user") != null) {
			user = (User) session.getAttribute("user");
			verified = true;
		}
	%>
	<!-- End User Verification -->

	<!-- Start Top Bar -->
	<div class="container" style="width: 100%;">
		<header>
			<div class="navbar">
				<div class="navbar-inner">
					<%
						// Display The Appropriate Navigation Bar Elements
						// Depending On Whether The User Is Verified
						if (!verified) {
					%>
					<ul class="nav pull-left">
						<li><a href="login.jsp"> Log In </a></li>

						<li><a href="registration.jsp"> Register </a></li>
					</ul>
					<%
						} else {
					%>
					<ul class="nav pull-left">
						<li><a> Welcome <%=user.getFullName()%></a></li>
					</ul>
					<ul class="nav pull-right">
						<li><a
							href="${pageContext.request.contextPath}/LogoutServlet"> Log
								Out</a>
								</li>
					</ul>
					<%
						}
					%>
				</div>
			</div>
		</header>
	</div>
	<!-- End Top Bar -->
</body>
</html>