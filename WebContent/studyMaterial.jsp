<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Study Material</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="iLearnCSS/iLearnCSS.css">
</head>
<body class="body">
	<!-- Refresh On Reload Script -->
	<jsp:include page="refreshOnReloadScript.jsp" />

	<!-- Start Imports -->
	<%@ page import="java.util.*"%>
	<%@ page import="java.sql.*"%>
	<%@ page import="edu.ben.cmsc398.iLearn.Dao.*"%>
	<%@ page import="edu.ben.cmsc398.iLearn.Model.*"%>
	<!-- End Imports -->

	<!-- Start User Verification -->
	<%
		// Initialize A User Object To Null
		User user = null;

		// Initialize A Boolean Variable To False
		boolean verified = false;

		// If A Session Exists and A User Object Is In The Session,
		// Change The User Object
		// To The One Retrieved From The Session
		if (session != null && session.getAttribute("user") != null) {
			user = (User) session.getAttribute("user");
			if (user.getType().equals("User")) {
				verified = true;
			}
		} else {
			response.sendRedirect("login.jsp");
		}
	%>
	<!-- End User Verification -->

	<!-- Static Top Bar -->
	<jsp:include page="topBar.jsp" />

	<%
		// If The User Is Verified Display The My Profile Page
		if (verified == true) {
	%>
	<!-- Start Page Container -->
	<div class="container" style="background-color: white; padding: 10px;">
		<!-- Static Header -->
		<jsp:include page="header.jsp" />

		<!-- Start Study Material -->
		<div class="well">
			<fieldset>
				<legend> Study Material </legend>
			</fieldset>

			<%
				// If The Package ID RequestParam Exists
					if (request.getParameter("packageID") != null) {
						PackageDAO packageDAO = new PackageDAO();
						DBConnector conn = new DBConnector();

						// If The User Owns The Package In The RequestParam
						if (packageDAO
								.userHasPackage(user.getiD(),
										Integer.parseInt(request
												.getParameter("packageID")), conn)) {

						} else {
			%>
			<div class="alert alert-error">
				<strong> You Do Not Own This Package! </strong>
			</div>
			<%
				}
					} else {
			%>
			<div class="alert alert-error">
				<strong> No Package Specified! </strong>
			</div>
			
			<%
				}
			%>

		</div>
		<!-- End Grades -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
	<%
		}
	%>

</body>
</html>