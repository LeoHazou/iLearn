<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="iLearnCSS/iLearnCSS.css">
</head>
<body class="body">

	<!-- Imports -->
	<%@ page import="javax.servlet.http.Cookie;"%>

	<!-- Refresh On Reload Script -->
	<jsp:include page="refreshOnReloadScript.jsp" />

	<!-- Start User Verification -->
	<%
		// If A Session Exists And A User Object Is In The Session,
		// Redirect The User To The Home JSP Page
		if (session != null && session.getAttribute("user") != null) {
			response.sendRedirect("index.jsp");
		}
	%>
	<!-- End User Verification -->

	<!-- Static Top Bar -->
	<jsp:include page="topBar.jsp" />

	<!-- Start Page Container -->
	<div class="container" style="background-color: white; padding: 10px;">
		<!-- Static Header -->
		<jsp:include page="header.jsp" />

		<!-- Start Login Page -->
		<div class="well">
			<fieldset>
				<legend> Log In </legend>
			</fieldset>

			<!-- Start Success Message -->
			<%
				if (request.getParameter("successMessage") != null) {
			%>
			<div class="alert alert-success">
				<strong> <%=request.getParameter("successMessage")%>
				</strong>
			</div>
			<%
				}
			%>
			<!-- End Success Message -->

			<!-- Start Message -->
			<%
				if (request.getParameter("message") != null) {
			%>
			<div class="alert alert-error">
				<strong> <%=request.getParameter("message")%>
				</strong>
			</div>
			<%
				}
			%>
			<!-- End Message -->

			<%
				// Get All Cookies, Search For The iLearnUserID Cookie
				// If It Exists, Generate The (Cookie Found) Login Form
				// Else, Generate The (Cookie Not Found) Login Form
				String cookieName = "iLearnUserID";
				Cookie[] cookies = request.getCookies();
				Cookie iLearnUserIDCookie = null;
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookieName.equals(cookie.getName())) {
							iLearnUserIDCookie = cookie;
						}
					}
				}

				if (iLearnUserIDCookie != null) {
			%>

			<!-- Start Login Form (Cookie Found) -->
			<form class="form-horizontal" name="loginForm" action="LoginServlet"
				method="POST">
				<div class="control-group">
					<label class="control-label">Email</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-envelope"></i></span> <input
								type="email" class="input-xlarge" placeholder="Email"
								name="email" value=<%=iLearnUserIDCookie.getValue()%>>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Password</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-lock"></i></span> <input
								type="Password" class="input-xlarge" placeholder="Password"
								name="password">
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<input type="submit" class="btn btn-primary" value="Log In">
						<label class="checkbox inline"> <input type="checkbox"
							name="rememberMe" value="True" checked="checked"> <a>
								Remember Me </a></label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<label> New User? <a href="registration.jsp"> Click
								Here To Register </a>
						</label> <label> Forgot Your Password? <a href="resetPassword.jsp">
								Click Here To Reset </a>
						</label>
					</div>
				</div>
			</form>
			<!-- End Login Form (Cookie Found) -->


			<%
				} else {
			%>

			<!-- Start Login Form (No Cookie Found) -->
			<form class="form-horizontal" name="loginForm" action="LoginServlet"
				method="POST">
				<div class="control-group">
					<label class="control-label">Email</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-envelope"></i></span> <input
								type="email" class="input-xlarge" placeholder="Email"
								name="email">
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Password</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-lock"></i></span> <input
								type="Password" class="input-xlarge" placeholder="Password"
								name="password">
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<input type="submit" class="btn btn-primary" value="Log In">
						<label class="checkbox inline"> <input type="checkbox"
							name="rememberMe" value="True"> <a> Remember Me </a></label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<label> New User? <a href="registration.jsp"> Click
								Here To Register </a>
						</label> <label> Forgot Your Password? <a href="resetPassword.jsp">
								Click Here To Reset </a>
						</label>
					</div>
				</div>
			</form>
			<!-- End Login Form (No Cookie Found) -->

			<%
				}
			%>


		</div>
		<!-- End Login Page -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
</body>
</html>