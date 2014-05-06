<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Personnel</title>
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
			// Get the rquired parameters
	%>
	<!-- Start Page Container -->
	<div class="container" style="background-color: white; padding: 10px;">
		<!-- Static Header -->
		<jsp:include page="header.jsp" />

		<!-- Start Edit Admin info Form -->
		<div class="well">
			<fieldset>
				<legend> Add Personnel </legend>
			</fieldset>

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

			<form class="form-horizontal" name="EditInfo Form"
				action="EditAdmin" method="POST">
				<div class="control-group">
					<label class="control-label">First Name</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge"
								placeholder="Type First Name Here" name="firstName" required <% if ( request.getParameter("firstName") != null){ %> value="<%=request.getParameter("firstName") %>" <% } %>>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Last Name</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge"
								placeholder="Type Last Name Here" name="lastName" required <% if ( request.getParameter("lastName") != null){ %> value="<%=request.getParameter("lastName") %>" <% } %>>
								
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Email</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="email" class="input-xlarge"
								placeholder="Type Email Here" name="email" <% if ( request.getParameter("email") != null){ %> value="<%= request.getParameter("email") %>" <% } %>
								required>
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">Password</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-lock"></i></span> <input
								type="Password" class="input-xlarge" placeholder="Password"
								name="password" required="required">
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Confirm Password</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-lock"></i></span> <input
								type="Password" class="input-xlarge"
								placeholder="Confirm Password" name="confirmPassword"
								required="required">
						</div>
					</div>
				</div>
				
				<div class="control-group ">
									<label class="control-label">Account Type</label>
									<div class="controls">
										<label class="radio inline"> <input type="radio"
											name="type" value="SuperAdministrator" required="required"
											<%if (request.getParameter("type") !=null && request.getParameter("type").equals("SuperAdministrator")) {%> checked="checked"
											<%}%>> <a>SuperAdministrator </a>
										</label> <label class="radio inline"> <input type="radio"
											name="type" value="Administrator" required="required"
											<%if (request.getParameter("type") !=null && request.getParameter("type").equals("Administrator")) {%>
											checked="checked" <%}%>> <a> Administrator</a>
										</label>
									</div>
								</div>
				<div class="control-group ">
									<label class="control-label">Date Of Birth</label>
									<div class="controls">
										<div class="input-prepend">
											<span class="add-on"><i class="icon-calendar"></i></span> <input
												name="dateOfBirth" type="date" class="input-xlarge" required="required"
												 <% if ( request.getParameter("dateOfBirth") != null){ %> value="<%=request.getParameter("dateOfBirth") %>" <% } %> >
										</div>
									</div>
								</div>
								
				<div class="control-group ">
									<label class="control-label">Gender</label>
									<div class="controls">
										<label class="radio inline"> <input type="radio"
											name="gender" value="Male" required="required"
											<%if (request.getParameter("gender") != null && request.getParameter("gender").equals("Male")) {%> checked="checked"
											<%}%>> <a>Male </a>
										</label> <label class="radio inline"> <input type="radio"
											name="gender" value="Female" required="required"
											<%if (request.getParameter("gender") != null && request.getParameter("gender").equals("Female")) {%>
											checked="checked" <%}%>> <a> Female</a>
										</label> <label class="radio inline"> <input type="radio"
											name="gender" value="Other" required="required"
											<%if (request.getParameter("gender") != null && request.getParameter("gender").equals("Other")) {%> checked="checked"
											<%}%>> <a> Other</a>
										</label>
									</div>
								</div>
								
								
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<input type="submit" class="btn btn-success"
							value="Add Administrator" name="function"> 
							<a href="manageAdministrators.jsp"><button type="button" class="btn btn-danger">Cancel and Return</button></a>
					</div>
				</div>
			</form>
		</div>
		<!-- End Add Questions Form -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
	<%
		}
	%>

</body>
</html>