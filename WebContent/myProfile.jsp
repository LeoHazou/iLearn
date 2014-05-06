<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile</title>
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
			verified = true;
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

		<!-- Start My Profile -->
		<div class="well">
			<div id="content">
				<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
					<li class="active"><a href="#viewProfile" data-toggle="tab">
							View Profile </a></li>
					<li><a href="#editProfile" data-toggle="tab"> Edit Profile
					</a></li>
					<li><a href="#changePassword" data-toggle="tab"> Change
							Password </a></li>
				</ul>

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

				<!-- Start View Profile -->
				<div id="my-tab-content" class="tab-content">
					<div class="tab-pane active" id="viewProfile">
						<div class="form-horizontal">
							<div class="control-group">
								<label class="control-label">Email</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-envelope"></i></span> <input
											type="email" class="input-xlarge" readonly="readonly"
											value="<%=user.getEmail()%>">
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">First Name</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-user"></i></span> <input
											type="text" class="input-xlarge" readonly="readonly"
											value="<%=user.getFirstName()%>">
									</div>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label">Last Name</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-user"></i></span> <input
											type="text" class="input-xlarge" readonly="readonly"
											value="<%=user.getLastName()%>">
									</div>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label">Date Of Birth</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-calendar"></i></span> <input
											type="date" class="input-xlarge" readonly="readonly"
											value="<%=user.getDateOfBirth()%>">
									</div>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label">Gender</label>
								<div class="controls">
									<div class="input-prepend">

										<span class="add-on"><i class="icon-user"></i></span> <input
											type="text" class="input-xlarge" readonly="readonly"
											value="<%=user.getGender()%>">
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- End View Profile -->

					<!-- Start Edit Profile -->
					<div class="tab-pane" id="editProfile">

						<!-- Start Edit Profile Form -->
						<form class="form-horizontal" name="editProfileForm"
							action="EditProfileServlet" method="POST">
							<div class="form-horizontal">
								<div class="control-group">
									<label class="control-label">Email</label>
									<div class="controls">
										<div class="input-prepend">
											<span class="add-on"><i class="icon-envelope"></i></span> <input
												name="email" type="email" class="input-xlarge"
												readonly="readonly" value="<%=user.getEmail()%>">
										</div>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">First Name</label>
									<div class="controls">
										<div class="input-prepend">
											<span class="add-on"><i class="icon-user"></i></span> <input
												name="firstName" type="text" class="input-xlarge"
												required="required" value="<%=user.getFirstName()%>">
										</div>
									</div>
								</div>
								<div class="control-group ">
									<label class="control-label">Last Name</label>
									<div class="controls">
										<div class="input-prepend">
											<span class="add-on"><i class="icon-user"></i></span> <input
												name="lastName" type="text" class="input-xlarge"
												required="required" value="<%=user.getLastName()%>">
										</div>
									</div>
								</div>
								<div class="control-group ">
									<label class="control-label">Date Of Birth</label>
									<div class="controls">
										<div class="input-prepend">
											<span class="add-on"><i class="icon-calendar"></i></span> <input
												name="dateOfBirth" type="date" class="input-xlarge"
												required="required" value="<%=user.getDateOfBirth()%>">
										</div>
									</div>
								</div>
								<div class="control-group ">
									<label class="control-label">Gender</label>
									<div class="controls">
										<label class="radio inline"> <input type="radio"
											name="gender" value="Male" required="required"
											<%if (user.getGender().equals("Male")) {%> checked="checked"
											<%}%>> <a>Male </a>
										</label> <label class="radio inline"> <input type="radio"
											name="gender" value="Female" required="required"
											<%if (user.getGender().equals("Female")) {%>
											checked="checked" <%}%>> <a> Female</a>
										</label> <label class="radio inline"> <input type="radio"
											name="gender" value="Other" required="required"
											<%if (user.getGender().equals("Other")) {%> checked="checked"
											<%}%>> <a> Other</a>
										</label>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"></label>
									<div class="controls">
										<input type="submit" class="btn btn-primary"
											value="Edit Profile">
									</div>
								</div>
							</div>
						</form>
						<!-- End Edit Profile Form -->

					</div>
					<!-- End Edit Profile -->

					<!-- Start Edit Profile -->
					<div class="tab-pane" id="changePassword">

						<!-- Start Edit Profile Form -->
						<form class="form-horizontal" name="changePasswordForm"
							action="ChangePasswordServlet" method="POST">
							<div class="form-horizontal">
								<div class="control-group">
									<label class="control-label">Current Password</label>
									<div class="controls">
										<div class="input-prepend">
											<span class="add-on"><i class="icon-lock"></i></span> <input
												name="password" type="password" class="input-xlarge"
												placeholder="Current Password">
										</div>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">New Password</label>
									<div class="controls">
										<div class="input-prepend">
											<span class="add-on"><i class="icon-lock"></i></span> <input
												name="newPassword" type="password" class="input-xlarge"
												required="required" placeholder="New Password">
										</div>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Confirm New Password</label>
									<div class="controls">
										<div class="input-prepend">
											<span class="add-on"><i class="icon-lock"></i></span> <input
												name="confirmNewPassword" type="password"
												class="input-xlarge" required="required"
												placeholder="Confirm New Password">
										</div>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"></label>
									<div class="controls">
										<input type="submit" class="btn btn-primary"
											value="Change Password">
									</div>
								</div>
							</div>
						</form>
					</div>


				</div>
			</div>
		</div>
		<!-- End My Profile -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />

	</div>
	<!-- End Page Container -->
	<%
		}
	%>

	<!-- Start Java Script -->
	<script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>

	<!-- End Java Script -->
</body>
</html>