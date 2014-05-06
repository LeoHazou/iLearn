<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="edu.ben.cmsc398.iLearn.Model.PackageBuilder"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List All Packages</title>
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

		<!-- Start Listall Package Form -->
		<div class="well">
			<fieldset>
				<legend> Package List</legend>
			</fieldset>

			<!-- Start Message -->
			<%
				ArrayList<PackageBuilder> packageList = new ArrayList<PackageBuilder>();
					packageList = (ArrayList<PackageBuilder>) request.getAttribute("packageList");
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

			<form class="form-horizontal">

				<%
					if (packageList.size() == 0) {
				%>
				<table border="1" style="width: 400px">
					<tr>
						<th>There are no packages available</th>
					</tr>
				</table>
				<%
					} else {
				%>

				<table class="table table-striped" style="width: 100%">
					<tr>
						<th>Package ID</th>
						<th>Package Name</th>
					</tr>
					<%
						for (int i = 0; i < packageList.size(); i++) {
					%>
					<tr <%if (i % 2 == 0) {%> class="alert-info" <%}%>>
						<td align="center"><%=packageList.get(i).getPkgID()%></td>
						<td align="center"><%=packageList.get(i).getPkgName()%></td>
					</tr>
					<%
						}
					%>
				</table>

				<%
					}
				%>

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