<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Package Page</title>
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
			if (user.getType().equals("Administrator") || user.getType().equals("SuperAdministrator"))
			{
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

		<!-- Start Add Package Form -->
		<div class="well">
			<fieldset>
				<legend> Add Package </legend>
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

			<form class="form-horizontal" name="AddPackageForm"
				action="AddPackage" method="POST">
				<div class="control-group">
					<label class="control-label">Package Name</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge" placeholder="Package Name" <% if ( request.getParameter("pkgName") != null){ %> value="<%=request.getParameter("pkgName") %>" <% } %> 
								name="pkgName" required>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Package Description</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge"
								placeholder="Package Description" <% if ( request.getParameter("pkgDescription") != null){ %> value="<%=request.getParameter("pkgDescription") %>" <% } %>name="pkgDescription" required>
						</div>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Package Price</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge" placeholder="Package Price" <% if ( request.getParameter("pkgPrice") != null){ %> value="<%=request.getParameter("pkgPrice") %>" <% } %>
								name="pkgPrice" required>
						</div>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<input type="submit" class="btn btn-primary"
							value="Start Adding Questions">
					</div>
				</div>


			</form>
		</div>
		<!-- End Add package Form -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
	<%
		}
	%>

</body>

</html>