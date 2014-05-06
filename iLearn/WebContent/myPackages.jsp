<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Packages</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="iLearnCSS/iLearnCSS.css">
</head>
<body class="body">
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

		<!-- Start My Packages -->
		<div class="well">
			<fieldset>
				<legend> My Packages </legend>
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
				// If The User Owns Atleast One Package, Display Their Package List, Otherwise Notify User
					if (!user.getPackageList().isEmpty()) {
			%>
			<table class="table table-striped">
				<tr>
					<th>Name</th>
					<th>Actions</th>
				</tr>
				<%
					// Show All The User's Package's
							for (PackageBuilder packageBuilder : user.getPackageList()) {
				%>
				<tr>
					<td><%=packageBuilder.getPkgName()%></td>
					

					<td>
					
		
							<a
						href=<%="quizDetails.jsp?packageID="
								+ packageBuilder.getPkgID()%>>
							<input type="submit" class="btn btn-info" value="Take Quiz">
					</a> <a
						href=<%="Report?uid=" + user.getiD() + "&pid="+ packageBuilder.getPkgID()%> target="_blank">
							<input type="submit" class="btn btn-success" value="View Grades" >
					</a>
					<% if ( !packageBuilder.getStudyMaterialLink().equals("")){ %>
						<a href="<%=packageBuilder.getStudyMaterialLink() %>" target="_blank"><button type="button" class="btn btn-warning">View Study Material</button></a>
						
							<%} %>
					</td>
				</tr>
				<%
					}
				%>
			</table>
			<%
				} else {
			%>
			<h5>You Do Not Own Any Packages!</h5>
			<%
				}
			%>
			<hr>
			<a href="shop.jsp"> <input type="submit" class="btn btn-primary"
				value="Purchase More Packages">
			</a>
		</div>
		<!-- End My Packages -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
	<%
		}
	%>

</body>
</html>