<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="edu.ben.cmsc398.iLearn.Model.PackageBuilder"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Search Results</title>
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
			if (user.getType().equals("SuperAdministrator"))
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

		<!-- Start Edit Questions Package Form -->
		<div class="well">

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

				ArrayList<User> users = (ArrayList<User>) request.getSession().getAttribute("userList");
					if ((String) request.getAttribute("message") != null) {
			%>
			<div class="alert alert-success">
				<strong> <%=(String) request.getAttribute("message")%>
				</strong>
			</div>
			<%
				}
			%>
			<!-- End Message -->

			<fieldset>
				<legend> User List</legend>
			</fieldset>

			<form class="form-horizontal">

				<%
					if (users.size()==0) {
				%>
				<table class="table table-striped" style="width: 100%">
					<tr>
						<th>No Results. Please Search Again.</th>
					</tr>
				</table>
				<%
					} else {
				%>
				<table class="table table-striped" style="width: 100%">
				<%
					for (int i = 0; i < users.size(); i++) {
				%>
				
					<tr>
						<td align ="left" width="20%">Name</td>
						<td align ="left"><%=users.get(i).getLastName()+", " + user.getFirstName()%></td>
					</tr>

					<tr>
						<td align ="left" width="20%">Email</td>
						<td align ="left"><%=users.get(i).getEmail()%></td>
					</tr>

					<tr>
						<td align ="left" width="20%">Password</td>
						<td align ="left">********</td>
					</tr>

					<tr>
						<td align ="left" width="20%">Date Of Birth</td>
						<td align ="left"><%=users.get(i).getDateOfBirth()%></td>
					</tr>

					<tr>
						<td align ="left" width="20%">Gender</td>
						<td align ="left"><%=users.get(i).getGender()%></td>
					</tr>

					<tr>
						<td align ="left" width="20%" >Type</td>
						<td align ="left"><%=users.get(i).getType() %></td>
					</tr>
					<tr><td></td>
						<td align="left">
									
									<a a target="_blank" href="ManageUser?method=POST&function=reset&ID=<%=users.get(i).getiD()%>"><button type="button" class="btn btn-info">RESET PASSWORD</button></a>
									<a href="manageUsers.jsp"><button type="button" class="btn btn-warning">CANCEL</button></a>
									</td>
									
						
									
					</tr>
<%} %>
				</table>
				<hr>

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