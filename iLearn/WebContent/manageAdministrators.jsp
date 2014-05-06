<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="edu.ben.cmsc398.iLearn.Model.PackageBuilder"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Administrators</title>
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
			response.sendRedirect("login.jsp?message=Access Denied");
		}
	%>
	<!-- End User Verification -->

	<!-- Static Top Bar -->
	<jsp:include page="topBar.jsp" />


	<%
		// If The User Is Verified Display The page
		if (verified == true) {
	%>
	<!-- Start Page Container -->
	<div class="container" style="background-color: white; padding: 10px;">
		<!-- Static Header -->
		<jsp:include page="header.jsp" />

		<!-- Start Manage admins Form -->
		<div class="well">


			<!-- Start Message -->
			<%

				ArrayList<User> users = (ArrayList<User>) request.getSession().getAttribute("adminList");
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

			<fieldset>
				<legend>Manage Administrators</legend>
			</fieldset>

			<form class="form-horizontal">

				<%
					if (users.size() == 0) {
				%>
				<table class="table table-striped" style="width: 100%">
					<tr>
						<th>There are no Administrators to be displayed at this time.</th>
					</tr>
				</table>
				<%
					} else {
				%>
				<%
					for (int i = 0; i < users.size(); i++) {
				%>
				<table class="table table-striped" style="width: 100%">

					<tr>
						<td align ="left" width="20%">Name</td>
						<td align ="left"><%=users.get(i).getLastName()%>, <%=users.get(i).getFirstName()%></td>
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
						<td align ="left" width="20%">Access Type</td>
						<td align ="left"><%=users.get(i).getType()%></td>
					</tr>

					<tr>
						<td align ="left" width="20%">Date Of Birth</td>
						<td align ="left"><%=users.get(i).getDateOfBirth()%></td>
					</tr>

					<tr>
						<td align ="left" width="20%">Gender</td>
						<td align ="left"><%=users.get(i).getGender()%></td>
					</tr>

					<tr><td align = "left" > <a href="addAdmin.jsp"><button type="button" class="btn btn-info">Add New Personnel</button></a></td>
						<td align="left"><a
							href="editAdminInfo.jsp?ID=<%=users.get(i).getiD()%>&firstName=<%=users.get(i).getFirstName()%>&lastName=<%=users.get(i).getLastName()%>&gender=<%=users.get(i).getGender()%>&dateOfBirth=<%=users.get(i).getDateOfBirth()%>&email=<%=users.get(i).getEmail()%>&type=<%=users.get(i).getType()%>"><button
									type="button" class="btn btn-success">EDIT</button></a>
									
									<%if (users.get(i).getiD() != user.getiD()) {%>
									<a target="_blank" href="EditAdmin?method=POST&function=reset&ID=<%=users.get(i).getiD()%>&email=<%=users.get(i).getEmail()%>&fname=<%=users.get(i).getFirstName()%>&lname=<%=users.get(i).getLastName()%>"><button
									type="button" class="btn btn-warning">RESET PASSWORD</button></a>
									
									 <a href="EditAdmin?method=POST&function=delete&ID=<%=users.get(i).getiD()%>"><button
									type="button" class="btn btn-danger">DELETE</button></a>
									<%} %>
									<a href="index.jsp"><button type="button" class="btn btn-primary">BACK</button></a>
									</td>
									
						
									
					</tr>

				</table>
				<hr>

				<%
					}
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