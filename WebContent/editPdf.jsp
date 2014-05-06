<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="edu.ben.cmsc398.iLearn.Model.PackageBuilder"%>
<%@page import="javax.servlet.http.HttpServlet"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Study Material PDF</title>
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

		<!-- Start Add PDF Form -->
		<div class="well">
			<fieldset>
				<legend> Edit Study Material </legend>
			</fieldset>


			<!-- Start Message -->
			<%
			
			PackageBuilder pkg = (PackageBuilder) request.getSession().getAttribute("pkg");
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

			<form  action="upload"  method="POST" enctype="multipart/form-data">
			
			<div class="alert alert-error">
						<% if (pkg.isStudyMaterial()) 
						{%>
						<a href="<%=pkg.getStudyMaterialLink() %>" target="_blank"><button type="button" class="btn btn-primary">View Current Study Material</button></a>
						<%} %>
						
						<% if (!pkg.isStudyMaterial()) 
						{%>
						<strong>There is No Study Material Currently Associated With This Package.</strong>
						<%} %>
						</div>
						
				
				
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<div class="input-prepend">
							<input
								type="file" class="input-xlarge" name="upload" >
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<input type="submit" class="btn btn-primary" value="Upload" name ="value">
						<a href="publish.jsp"><input type="button" class="btn btn-primary" value="Skip"></a>
						<a href="index.jsp"><button type="button" class="btn btn-warning">Cancel</button></a>
					</div>
				</div>	
			</form>
		</div>
		<!-- End edit pdf Form -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
	<% } %>
	
</body>

</html>