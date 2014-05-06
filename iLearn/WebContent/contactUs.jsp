<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact Us Page</title>
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

	<!-- Static Top Bar -->
	<jsp:include page="topBar.jsp" />

	<!-- Start Page Container -->
	<div class="container" style="background-color: white; padding: 10px;">
		<!-- Static Header -->
		<jsp:include page="header.jsp" />

		<!-- Start Contact Us -->
		<div class="well">
			<fieldset>
				<legend> Contact Us </legend>
			</fieldset>
			
			<div>

				<form class="form-horizontal" name="contactUsForm"
					action="contactUsServlet" method="POST">

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

					<div class="control-group ">
						<label class="control-label">Question Or Request</label>
						<div class="controls">
							<div class="input-prepend">
								<input type="text" class="input-xxlarge"
									placeholder="Question Or Request" name="lastName"
									required="required">
							</div>
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
							<input type="submit" class="btn btn-primary" value="Submit">
						</div>
					</div>

				</form>
			</div>
			<!-- End Contact Us -->

			<!-- Static Footer -->
			<jsp:include page="footer.jsp" />
		</div>
		<!-- End Page Container -->
</body>
</html>