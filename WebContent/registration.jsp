<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="iLearnCSS/iLearnCSS.css">

<!-- reCaptcha Script -->
<script type="text/javascript">
	var RecaptchaOptions = {
		theme : 'clean'
	};
</script>

</head>
<body class="body">
	<!-- Start Page Imports -->
	<%@ page import="net.tanesha.recaptcha.*"%>
	<!-- End Page Imports -->

	<!-- Refresh On Reload Script -->
	<jsp:include page="refreshOnReloadScript.jsp" />

	<!-- Start User Verification -->
	<%
		// If A Session Exists And A User Object Is In The Session,
		// Redirect The User To The Home JSP Page
		if (session != null && session.getAttribute("user") != null) {
			response.sendRedirect("index.jsp");
		}
	%>
	<!-- End User Verification -->

	<!-- Static Top Bar -->
	<jsp:include page="topBar.jsp" />

	<!-- Start Page Container -->
	<div class="container" style="background-color: white; padding: 10px;">
		<!-- Static Header -->
		<jsp:include page="header.jsp" />

		<!-- Start Page Container -->
		<div class="well">
			<fieldset>
				<legend> Register </legend>
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

			<!-- Start Registration Form -->
			<form class="form-horizontal" name="registrationForm"
				action="RegistrationServlet" method="POST">
				<div class="control-group">
					<label class="control-label">Email</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-envelope"></i></span> <input
								type="email" class="input-xlarge" placeholder="Email"
								name="email" required="required">
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
				<div class="control-group">
					<label class="control-label">First Name</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-user"></i></span> <input
								type="text" class="input-xlarge" placeholder="First Name"
								name="firstName" required="required">
						</div>
					</div>
				</div>
				<div class="control-group ">
					<label class="control-label">Last Name</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-user"></i></span> <input
								type="text" class="input-xlarge" placeholder="Last Name"
								name="lastName" required="required">
						</div>
					</div>
				</div>
				<div class="control-group ">
					<label class="control-label">Date Of Birth</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-calendar"></i></span> <input
								type="date" class="input-xlarge" name="dateOfBirth"
								required="required">
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Gender</label>
					<div class="controls">
						<label class="radio inline"> <input type="radio"
							name="gender" value="Male" required="required"> <a>Male
						</a>
						</label> <label class="radio inline"> <input type="radio"
							name="gender" value="Female" required="required"> <a>
								Female</a>
						</label> <label class="radio inline"> <input type="radio"
							name="gender" value="Other" required="required"> <a>
								Other</a>
						</label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Captcha</label>
					<div class="controls">
						<%
							ReCaptcha reCaptcha = ReCaptchaFactory.newReCaptcha(
									"6LcYQPASAAAAAJyr_uDi7G1_2cXYy6cAsvKAo8Ah",
									"6LcYQPASAAAAACVEM0-ImH7o6zGT-Z9Na9ptfBX2", false);
							out.print(reCaptcha.createRecaptchaHtml(null, null));
						%>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<input type="submit" class="btn btn-primary" value="Register">
					</div>
				</div>
			</form>
		</div>
		<!-- End Registration Form -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
</body>
</html>