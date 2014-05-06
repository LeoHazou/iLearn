<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About Us Page</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="iLearnCSS/iLearnCSS.css">
</head>
<body class="body">
	<!-- Refresh On Reload Script -->
	<jsp:include page="refreshOnReloadScript.jsp" />

	<!-- Static Top Bar -->
	<jsp:include page="topBar.jsp" />

	<!-- Start Page Container -->
	<div class="container" style="background-color: white; padding: 10px;">
		<!-- Static Header -->
		<jsp:include page="header.jsp" />

		<!-- Start About Us -->
		<div class="well">
			<fieldset>
				<legend> About Us </legend>
			</fieldset>
			<p>iLearn's goal is to support individuals with learning
				disabilities through innovative computer based instruction, which
				can narrow the gap between them and their peers.</p>
			<p>An individual's future is determined by their ability to
				master subjects such as math, science, and reading. Nevertheless,
				costs, class sizes, and other issues often prevent individuals from
				accessing the proper attention and quality teaching that an
				individual deserves. iLearn addresses these obstacles through its
				innovative methods allowing individuals to overcome academic
				weaknesses and deficits. iLearn gives students, the opportunity to
				expand their learning beyond what they are being taught in school.</p>
			<p>We use technology in innovative ways to help improve student
				achievement in school and at home. We are focused in delivering the
				best quality results in everything we do.</p>
		</div>
		<!-- End About Us -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
</body>
</html>