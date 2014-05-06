<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Details</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="iLearnCSS/iLearnCSS.css">
</head>
<body class="body">
	<!-- Refresh On Reload Script -->
	<jsp:include page="refreshOnReloadScript.jsp" />

	<!-- Start Imports -->
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

		<!-- Start Quiz Details -->
		<div class="well">
			<fieldset>
				<legend> Quiz Details </legend>
			</fieldset>

			<%
				// If The Package ID RequestParam Exists
					// If The User Owns The Package In The RequestParam
					if (request.getParameter("packageID") != null) {
						PackageDAO packageDAO = new PackageDAO();
						DBConnector conn = new DBConnector();
						if (packageDAO
								.userHasPackage(user.getiD(),
										Integer.parseInt(request
												.getParameter("packageID")), conn)) {
							PackageBuilder packageBuilder = packageDAO.getPkg(
									Integer.parseInt(request
											.getParameter("packageID")), conn);
			%>

			<table class="table table-striped">
				<tr>
					<th>Package Name:</th>
					<td><%=packageBuilder.getPkgName()%></td>
				</tr>
				<tr>
					<th>Package Description:</th>
					<td><%=packageBuilder.getPkgDescription()%></td>
				</tr>
				<tr>
					<th>Time Allowed:</th>
					<td>Unlimited</td>
				</tr>
			</table>
			<hr>
			<a
				href=<%="quiz.jsp?packageID="
								+ request.getParameter("packageID")%>>
				<input type="submit" class="btn btn-primary" value="Start Quiz">
			</a>
			<%
				conn.closeConnection();
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
		<!-- End Quiz Details -->


		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
	<%
		}
	%>

</body>
</html>